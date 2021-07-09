package com.adincuff.breedingregister.Horse;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HorseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private HorseService horseService;

    @Test
    public void ShouldReturnHttp200WhenGetRequest() throws Exception {
        mockMvc.perform(get("/api/horse"))
                .andExpect(status().isOk());
    }

    @Test
    public void getHorsesEndPointShouldReturnHorses() throws Exception {
        when(horseService.getAllHorses()).thenReturn(List.of(
                new Horse("507f191e810c19729de860ea", "exeko camara", Gender.GELDING, Color.BAY, Race.FRENCH_SADDLEBRED, LocalDate.of(2014, 06, 24), "217h391e810c19729de860ml"),
                new Horse("507f191e810c10160rv860ui", "jackyria camara", Gender.FEMALE, Color.BAY, Race.FRENCH_SADDLEBRED, LocalDate.of(2019, 04, 11), "507f191e810c19723de860po"),
                new Horse("507f191e765c10320rv123jn", "jacasse camara", Gender.GELDING, Color.GREY, Race.THOROUGHBRED, LocalDate.of(2019, 02, 21), "507f191e810c19723de860po")
        ));

        mockMvc.perform(get("/api/horse"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
