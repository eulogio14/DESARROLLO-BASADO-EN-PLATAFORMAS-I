package org.lab.week02lab02.controller;

import org.lab.week02lab02.model.Genre;
import org.lab.week02lab02.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/new")
    public ResponseEntity<Genre> createSong(@RequestBody Genre song) {
        Genre savedSong = genreService.save(genre);
        return ResponseEntity.ok(savedSong);
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> songs = genreService.findAll();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getSongById(@PathVariable long id) {
        Genre genre = genreService.findById(id);
        if (genre != null) {
            return ResponseEntity.ok(genre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSong(@RequestParam("id") long id) {
        songService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
