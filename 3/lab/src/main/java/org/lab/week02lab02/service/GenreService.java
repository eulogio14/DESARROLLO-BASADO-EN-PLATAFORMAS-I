package org.lab.week02lab02.service;

import org.lab.week02lab02.model.Genre;
import org.lab.week02lab02.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre save(Genre genre) {
        if (genre != null && genre.getTitle() != null && !genre.getTitle().isEmpty()) {
             return songRepository.save(song);
        } else {
            throw new IllegalArgumentException("Song title cannot be null or empty");
        }
    }

    public Genre findById(long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }

    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }
}
