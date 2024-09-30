package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCarBrand_returnsOnlyPorscheAllowed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/mercedes"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(containsString("Only 'porsche' allowed")));
    }

    @Test
    void getAllCars_returnsNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(containsString("No Cars found")));
    }
}