package com.adincuff.breedingregister.Horse;

import com.adincuff.breedingregister.User.User;
import com.adincuff.breedingregister.User.UserNotFoundException;
import com.adincuff.breedingregister.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorseService {

    @Autowired
    private HorseRepository horseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Horse> getAllHorses() {
        return horseRepository.findAll();
    }

    public List<Horse> getHorsesByUser(String userId) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException((String.format("User with id: '%s' not found", userId)));
        } else {
            return horseRepository.findByUserId(userId);
        }
    }

    public Horse getHorseById(String horseId) {

        Optional<Horse> horse = horseRepository.findById(horseId);

        if (horse.isEmpty()) {
            throw new HorseNotFoundException((String.format("Horse with id: '%s' not found", horseId)));
        } else {
            return horse.get();
        }
    }

    public String addNewHorse(HorseRequest horseRequest) {

        Horse horseToAdd = new Horse(horseRequest.getName(), horseRequest.getGender(), horseRequest.getColor(), horseRequest.getRace(), horseRequest.getDateOfBirth(), horseRequest.getUserId());

        Optional<User> user = userRepository.findById(horseRequest.getUserId());

        if (user.isEmpty()) {
            throw new UserNotFoundException((String.format("User with id: '%s' not found", horseRequest.getUserId())));
        } else {
            Horse horse = horseRepository.save(horseToAdd);
            return horse.getId();
        }
    }
}
