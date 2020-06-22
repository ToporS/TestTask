package ru.topor.testtask.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class CameraDataService {

    @Autowired
    private RestTemplate restTemplate;

    public JsonNode getResponseAsJsonRoot(URI uri) {
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        JsonNode root = null;
        try {
            root = mapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return root;
    }


   public <T> T getResponseAsObject(URI uri, Class<T> aClass) {
        return restTemplate.getForObject(uri, aClass);
    }
}
