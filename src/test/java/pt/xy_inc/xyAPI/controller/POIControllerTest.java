package pt.xy_inc.xyAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pt.xy_inc.xyAPI.controller.dtoRequest.POIRequestDTO;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class POIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ShouldReturnListOfPois() throws Exception {

        mockMvc.perform(get("/pois")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThan(1))));
    }

    @Test
    void shouldReturnCreatedPoi() throws Exception {
        POIRequestDTO testPoiRequestDTO = new POIRequestDTO();
        testPoiRequestDTO.setNomePoi("Padaria");
        testPoiRequestDTO.setCoordenadaX(22);
        testPoiRequestDTO.setCoordenadaY(13);

        String poiRequestJson = new ObjectMapper().writeValueAsString(testPoiRequestDTO);

        mockMvc.perform(post("/pois")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(poiRequestJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nomePoi", is("Padaria")))
                .andExpect(jsonPath("$.coordenadaX", is(22)))
                .andExpect(jsonPath("$.coordenadaY", is(13)));
    }

    @Test
    void shouldReturnPoisByCoordinates() throws Exception {

        mockMvc.perform(get("/pois/20/10/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThan(3))));
    }

    @Test
    void shouldReturnBadRequestWhenXIsNegative() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pois/-1/10/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("Coordinates must not be null"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void shouldReturnBadRequestWhenYIsNegative() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pois/10/-1/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("Coordinates must not be null"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void shouldReturnBadRequestWhenDmaxIsNegative() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pois/10/10/-1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("Coordinates must not be null"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void shouldReturnEmptyArrayWhenNoPoisFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pois/1000/1000/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
