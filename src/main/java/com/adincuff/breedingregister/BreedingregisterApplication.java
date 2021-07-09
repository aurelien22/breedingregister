package com.adincuff.breedingregister;

import com.adincuff.breedingregister.Horse.*;
import com.adincuff.breedingregister.User.User;
import com.adincuff.breedingregister.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class BreedingregisterApplication implements CommandLineRunner {

	@Autowired
	HorseRepository horseRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BreedingregisterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		horseRepository.deleteAll();
		userRepository.deleteAll();

		userRepository.save(new User("217h391e810c19729de860ml", "Jennifer"));
		userRepository.save(new User("507f191e810c19723de860po", "Aurélien"));

		horseRepository.save(new Horse("507f191e810c19729de860ea", "exeko camara", Gender.GELDING, Color.BAY, Race.FRENCH_SADDLEBRED, LocalDate.of(2014, 06, 24), "217h391e810c19729de860ml"));
		horseRepository.save(new Horse("507f191e810c10160rv860ui", "jackyria camara", Gender.FEMALE, Color.BAY, Race.FRENCH_SADDLEBRED, LocalDate.of(2019, 04, 11), "507f191e810c19723de860po"));
		horseRepository.save(new Horse("507f191e765c10320rv123jn", "jacasse camara", Gender.GELDING, Color.GREY, Race.THOROUGHBRED, LocalDate.of(2019, 02, 21), "507f191e810c19723de860po"));

	}
}