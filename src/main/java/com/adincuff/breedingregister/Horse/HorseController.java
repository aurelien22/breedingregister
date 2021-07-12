package com.adincuff.breedingregister.Horse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/horse")
public class HorseController {

    @Autowired
    private HorseService horseService;

    public HorseController(HorseService horseService) {
        this.horseService = horseService;
    }

    @GetMapping
    public ResponseEntity<List<Horse>> getHorses() {
        return ResponseEntity.ok(horseService.getAllHorses());
    }

    @GetMapping("/{horseId}")
    public ResponseEntity<Horse> getHorseById(@PathVariable("horseId") String horseId) {
        return ResponseEntity.ok(horseService.getHorseById(horseId));
    }
}
