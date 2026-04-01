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
    static {
        characters.add(new MarvelCharacter(nextId++, "Spider-Man", "Peter Parker", "Avengers", "Amazing Fantasy #15 (1962)", "Wall-crawling, web-shooting, spider-sense", "Bitten by radioactive spider"));
        characters.add(new MarvelCharacter(nextId++, "Iron Man", "Tony Stark", "Avengers", "Tales of Suspense #39 (1963)", "Powered armor suit, genius intellect", "Built suit to escape captivity"));
        characters.add(new MarvelCharacter(nextId++, "Captain America", "Steve Rogers", "Avengers", "Captain America Comics #1 (1941)", "Super soldier serum, shield", "Enhanced by super soldier serum"));
        characters.add(new MarvelCharacter(nextId++, "Thor", "Thor Odinson", "Avengers", "Journey into Mystery #83 (1962)", "Lightning, super strength, Mjolnir", "Asgardian God of Thunder"));
        characters.add(new MarvelCharacter(nextId++, "Black Widow", "Natasha Romanoff", "Avengers", "Tales of Suspense #52 (1964)", "Master spy, martial arts", "Trained assassin and spy"));
        characters.add(new MarvelCharacter(nextId++, "Hulk", "Bruce Banner", "Avengers", "The Incredible Hulk #1 (1962)", "Super strength, invulnerability", "Gamma radiation accident"));
        characters.add(new MarvelCharacter(nextId++, "Doctor Strange", "Stephen Strange", "Defenders", "Strange Tales #110 (1963)", "Magic, mystical arts", "Former surgeon turned Sorcerer Supreme"));
        characters.add(new MarvelCharacter(nextId++, "Wolverine", "James Logan Howlett", "X-Men", "The Incredible Hulk #180 (1974)", "Healing factor, adamantium claws", "Mutant with enhanced senses"));
        characters.add(new MarvelCharacter(nextId++, "Storm", "Ororo Munroe", "X-Men", "Giant-Size X-Men #1 (1975)", "Weather control", "Mutant with weather manipulation"));
        characters.add(new MarvelCharacter(nextId++, "Deadpool", "Wade Wilson", "X-Force", "The New Mutants #98 (1991)", "Healing factor, skilled marksman", "Former mercenary with accelerated healing"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<MarvelCharacter> getCharacterById(@PathVariable Long id) {
        for (MarvelCharacter character : characters) {
            if (character.getId().equals(id)) {
                return ResponseEntity.ok(character);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<MarvelCharacter>> getAllCharacters(@RequestParam(required = false) String team,
                                                                  @RequestParam(required = false) String name) {
        List<MarvelCharacter> result = new ArrayList<>(characters);

        if (team != null && !team.isEmpty()) {
            result = result.stream()
                    .filter(character -> character.getTeam().equalsIgnoreCase(team))
                    .collect(Collectors.toList());
        }

        if (name != null && !name.isEmpty()) {
            result = result.stream()
                    .filter(character -> character.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<MarvelCharacter> createCharacter(@RequestBody MarvelCharacter character) {
        character.setId(nextId++);
        characters.add(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(character);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MarvelCharacter> updateCharacter(@PathVariable Long id, @RequestBody MarvelCharacter updatedCharacter) {
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getId().equals(id)) {
                updatedCharacter.setId(id);
                characters.set(i, updatedCharacter);
                return ResponseEntity.ok(updatedCharacter);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getId().equals(id)) {
                characters.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/team/{team}")
    public ResponseEntity<List<MarvelCharacter>> getCharactersByTeamPath(@PathVariable String team) {
        List<MarvelCharacter> result = new ArrayList<>();
        for (MarvelCharacter character : characters) {
            if (character.getTeam().equalsIgnoreCase(team)) {
                result.add(character);
            }
        }
        return ResponseEntity.ok(result);
    }


    public static void main(String[] args) {
        SpringApplication.run(Week1Lab2Application.class, args);
    }

}
