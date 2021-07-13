package com.adincuff.breedingregister.Horse;

import com.mongodb.lang.NonNull;

import java.time.LocalDate;

public class HorseRequest {

    @NonNull
    private String name;
    private Gender gender;
    private Color color;
    private Race race;
    private LocalDate dateOfBirth;
    @NonNull
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
