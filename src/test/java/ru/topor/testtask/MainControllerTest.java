package ru.topor.testtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.topor.testtask.controllers.MainController;
import ru.topor.testtask.services.CameraDataService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private MainController controller;
    @Autowired
    CameraDataService cameraDataService;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(cameraDataService).isNotNull();
    }

    @Test
    public void mainControllerTest() throws Exception {
        mvc.perform(get("http://localhost:8080/cameras")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].urlType", is("ARCHIVE")))
                .andExpect(jsonPath("$[2].value", is("fa4b5d52-249b-11e9-ab14-d663bd873d93")))
                .andExpect(jsonPath("$[3].ttl", is(180)));
    }

}
