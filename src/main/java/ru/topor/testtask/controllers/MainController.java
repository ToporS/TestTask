package ru.topor.testtask.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topor.testtask.models.CameraData;
import ru.topor.testtask.models.CameraMetadata;
import ru.topor.testtask.services.CameraDataService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class MainController {

    @Autowired
    ThreadPoolTaskExecutor executor;
    @Autowired
    CameraDataService cameraDataService;

    @GetMapping("/cameras")
    public List<CameraData> getCamerasList()
    {
        Callable<List<CameraData>> work = this::doWork;
        Future<List<CameraData>> workComplete = executor.submit(work);
        try {
            return workComplete.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private List<CameraData> doWork()
    {
        JsonNode sourceDataUrlRoot, tokenDataUrlRoot;
        try {
            List<CameraData> cameraDataList = new ArrayList<CameraData>();
            CameraMetadata[] camerasMetadataList = cameraDataService.getResponseAsObject(new URI("http://www.mocky.io/v2/5c51b9dd3400003252129fb5"), CameraMetadata[].class);
            for(CameraMetadata cmd: camerasMetadataList)
            {
                CameraData cameraData = new CameraData();
                cameraData.setId(cmd.getId());
                Callable<JsonNode> sourceDataUrlResponse = () -> cameraDataService.getResponseAsJsonRoot(new URI(cmd.getSourceDataUrl()));
                Callable<JsonNode> tokenDataUrlResponse = () -> cameraDataService.getResponseAsJsonRoot(new URI(cmd.getTokenDataUrl()));
                Future<JsonNode> sourceDataUrlResponseRoot = executor.submit(sourceDataUrlResponse);
                Future<JsonNode> tokenDataUrlResponseRoot = executor.submit(tokenDataUrlResponse);
                sourceDataUrlRoot = sourceDataUrlResponseRoot.get();
                tokenDataUrlRoot = tokenDataUrlResponseRoot.get();
                cameraData.setUrlType(sourceDataUrlRoot.path("urlType").textValue());
                cameraData.setVideoUrl(sourceDataUrlRoot.path("videoUrl").textValue());
                cameraData.setValue(tokenDataUrlRoot.path("value").textValue());
                cameraData.setTtl(tokenDataUrlRoot.path("ttl").intValue());
                cameraDataList.add(cameraData);
            }
            return cameraDataList;
        } catch (URISyntaxException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
