package com.adincuff.breedingregister.Horse;

import com.adincuff.breedingregister.User.User;
import com.adincuff.breedingregister.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

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
    void shouldCallTheRepositoryWhenGetAllHorsesFunctionIsCall() {

        List<Horse> horses = horseService.getAllHorses();

        verify(horseRepository).findAll();

    }

    @Test
    void shouldCallTheRepositoryWhenGetAllHorsesByUserFunctionIsCall() {

        when(userRepository.findById("217h391e810c19729de860ml")).thenReturn(java.util.Optional.of(new User("217h391e810c19729de860ml", "Aurélien")));

        List<Horse> horses = horseService.getHorsesByUser("217h391e810c19729de860ml");

        verify(horseRepository).findByUserId("217h391e810c19729de860ml");

    }
}
