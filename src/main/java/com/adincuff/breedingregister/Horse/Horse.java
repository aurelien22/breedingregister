package com.adincuff.breedingregister.Horse;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Horse {

    @Id
    private String id;
    private String name;
    private Gender gender;
    private Color color;
    private Race race;
    private LocalDate dateOfBirth;
    private String userId;

    public Horse(String id, String name, Gender gender, Color color, Race race, LocalDate dateOfBirth, String userId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.color = color;
        this.race = race;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
    }

    public Horse(String name, Gender gender, Color color, Race race, LocalDate dateOfBirth, String userId) {
        this.name = name;
        this.gender = gender;
        this.color = color;
        this.race = race;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Color getColor() {
        return color;
    }

    public Race getRace() {
        return race;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUserId() {
        return userId;
    }
}
