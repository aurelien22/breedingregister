package com.adincuff.breedingregister.Horse;

import com.adincuff.breedingregister.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
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
}
