package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAnimalSpecies_returnsOnlyDogIsAllowed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/cat"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(containsString("Only 'dog' is allowed")));
    }

    @Test
    void getAllAnimals_returnsNoAnimalsFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(containsString("No Animals found")));
    }
}