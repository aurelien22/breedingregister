package com.adincuff.breedingregister.Horse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HorseController.class)
public class HorseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HorseService horseService;

    @Captor
    private ArgumentCaptor<HorseRequest> horseRequestArgumentCaptor;

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

    @Test
    public void ShouldReturnHttp200WhenGetAHorseRequest() throws Exception {

        mockMvc.perform(get("/api/horse/507f191e810c19729de860ea"))
                .andExpect(status().isOk());
    }

    @Test
    public void getHorseWithIdShouldReturnAHorse() throws Exception {

        when(horseService.getHorseById("507f191e810c19729de860ea")).thenReturn(
                new Horse("507f191e810c19729de860ea", "exeko camara", Gender.GELDING, Color.BAY, Race.FRENCH_SADDLEBRED, LocalDate.of(2014, 06, 24), "217h391e810c19729de860ml")
        );

        mockMvc.perform(get("/api/horse/507f191e810c19729de860ea"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("exeko camara")));
    }

    @Test
    public void getHorseWithUnknownIdShouldReturn404() throws Exception {

        when(horseService.getHorseById("507f191e810c19729de860pp")).thenThrow(new HorseNotFoundException("Horse not found"));

        mockMvc.perform(get("/api/horse/507f191e810c19729de860pp"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postedANewHorseShouldCreateANewHorse() throws Exception {
        HorseRequest horseRequest = new HorseRequest();
        horseRequest.setName("exeko camara");
        horseRequest.setGender(Gender.GELDING);
        horseRequest.setColor(Color.BAY);
        horseRequest.setRace(Race.FRENCH_SADDLEBRED);
        horseRequest.setDateOfBirth(LocalDate.of(2014, 06, 24));
        horseRequest.setUserId("217h391e810c19729de860ml");

        when(horseService.addNewHorse(horseRequestArgumentCaptor.capture())).thenReturn("507f191e810c19729de860ea");

        mockMvc.perform(post("/api/horse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(horseRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/horse/507f191e810c19729de860ea"));

        assertThat(horseRequestArgumentCaptor.getValue().getName(), is("exeko camara"));

    }

}
