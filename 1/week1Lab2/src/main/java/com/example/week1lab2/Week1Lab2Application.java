package com.example.week1lab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.example.week1lab2.MarvelCharacter;
@SpringBootApplication
@RestController
@RequestMapping("/api/characters")
public class Week1Lab2Application {
    private static final List<MarvelCharacter> characters = new ArrayList<>();
    private static long nextId = 1;
    static{
        characters.add(new MarvelCharacter(nextId++, "Spider-man", "Peter Parker", "Avengers",
                "Amazing Fantasy #15 (1962)",
                "Wall-crawling, web-shooting, spider-sense",
                " Bitten by a radioactive spider"
        ));
    }
    @GetMapping
    public ResponseEntity<List<MarvelCharacter>> getAllCharacters() {
        List<MarvelCharacter> result = new ArrayList<>(characters);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<MarvelCharacter> createCharacter(@RequestBody MarvelCharacter character) {
        character.setId(nextId++);
        characters.add(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(character);
    }

    public static void main(String[] args) {
        SpringApplication.run(Week1Lab2Application.class, args);
    }

}
