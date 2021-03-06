package com.adincuff.breedingregister.User;

import com.adincuff.breedingregister.Horse.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    HorseService horseService;

    @Test
    public void ShouldReturnHttp200WhenGetUserHorsesRequest() throws Exception {

        String userId = "217h391e810c19729de860ml";

        when(horseService.getHorsesByUser(userId)).thenReturn(List.of(
                new Horse("507f191e810c19729de860ea", "exeko camara", Gender.GELDING, Color.BAY, Race.FRENCH_SADDLEBRED, LocalDate.of(2014, 06, 24), "217h391e810c19729de860ml")
        ));

        mockMvc.perform(get("/api/user/217h391e810c19729de860ml/horses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void getHorsesWithUnknownUserIdShouldReturn404() throws Exception {

        String userId = "217h391e810c19729de860az";

        when(horseService.getHorsesByUser(userId)).thenThrow(new UserNotFoundException("User with id '217h391e810c19729de860az' not found"));

        mockMvc.perform(get("/api/user/217h391e810c19729de860az/horses"))
                .andExpect(status().isNotFound());
    }

}
