package ru.topor.testtask;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.topor.testtask.models.CameraMetadata;
import ru.topor.testtask.services.CameraDataService;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CameraDataServiceTests {

	@Autowired
	CameraDataService cameraDataService;

	@Test
	public void cameraMetaDataServiceTest() {
		try {
				CameraMetadata[] camerasMetadataList = cameraDataService.getResponseAsObject(new URI("http://www.mocky.io/v2/5c51b9dd3400003252129fb5"), CameraMetadata[].class);
				assertEquals(camerasMetadataList.length, 4);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void sourceDataUriTest()
	{
		try {
			JsonNode node = cameraDataService.getResponseAsJsonRoot(new URI("http://www.mocky.io/v2/5c51b230340000094f129f5d"));
			assertNotNull(node);
			assertEquals(node.path("urlType").asText(), "LIVE");
			assertEquals(node.path("videoUrl").asText(), "rtsp://127.0.0.1/1");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void tokenDataUriTest()
	{
		try {
			JsonNode node = cameraDataService.getResponseAsJsonRoot(new URI("http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s"));
			assertNotNull(node);
			assertEquals(node.path("value").asText(), "fa4b588e-249b-11e9-ab14-d663bd873d93");
			assertEquals(node.path("ttl").asInt(), 120);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}


}
