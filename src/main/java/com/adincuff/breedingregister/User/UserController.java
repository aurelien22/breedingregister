package com.adincuff.breedingregister.User;

import com.adincuff.breedingregister.Horse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private HorseService horseService;

    @GetMapping("/{userId}/horses")
    public ResponseEntity<List<Horse>> getHorsesByUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(horseService.getHorsesByUser(userId));
    }

}
