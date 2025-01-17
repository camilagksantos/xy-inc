package pt.xy_inc.xyAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pt.xy_inc.xyAPI.controller.dtoRequest.POIRequestDTO;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andDo(MockMvcResultHandlers.print())
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
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)));
    }
}
