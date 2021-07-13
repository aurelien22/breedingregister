package com.adincuff.breedingregister.Horse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping
    public ResponseEntity<Void> addANewHorse(@RequestBody HorseRequest horseRequest, UriComponentsBuilder uriComponentsBuilder) {

        String horseId = horseService.addNewHorse(horseRequest);

        UriComponents uriComponents = uriComponentsBuilder.path("/api/horse/{id}").buildAndExpand(horseId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
    }
}
