package com.adincuff.breedingregister.Horse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HorseNotFoundException extends RuntimeException {
    public HorseNotFoundException(String message) {
        super(message);
    }
}
