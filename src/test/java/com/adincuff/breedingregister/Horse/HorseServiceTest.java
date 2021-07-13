package com.adincuff.breedingregister.Horse;

import com.adincuff.breedingregister.User.User;
import com.adincuff.breedingregister.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HorseServiceTest {

    @Mock
    private HorseRepository horseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private HorseService horseService;

    @Test
    void shouldCallTheRepositoryWhenGetAllHorsesIsCall() {

        List<Horse> horses = horseService.getAllHorses();

        verify(horseRepository).findAll();

    }

    @Test
    void shouldCallTheRepositoryWhenGetAllHorsesByUserIsCall() {

        when(userRepository.findById("217h391e810c19729de860ml")).thenReturn(java.util.Optional.of(new User("217h391e810c19729de860ml", "Aurélien")));

        List<Horse> horses = horseService.getHorsesByUser("217h391e810c19729de860ml");

        verify(horseRepository).findByUserId("217h391e810c19729de860ml");

    }

    @Test
    void shouldCallTheRepositoryWhenGetHorseByIdIsCall() {

        when(horseRepository.findById("507f191e810c19729de860ea")).thenReturn(Optional.of(new Horse("507f191e810c19729de860ea", "exeko camara", Gender.GELDING, Color.BAY, Race.FRENCH_SADDLEBRED, LocalDate.of(2014, 06, 24), "217h391e810c19729de860ml")));

        Horse horse = horseService.getHorseById("507f191e810c19729de860ea");

        verify(horseRepository).findById("507f191e810c19729de860ea");
    }
}
