package com.projet.listCinema.controller;

import com.projet.listCinema.service.MovieService;
import com.projet.listCinema.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getListMovie(){
        return movieService.getAllMovies();
    }

    @GetMapping(value = "/allSortByRating")
    public List<Movie> getListMovieSortByRating(){
        return movieService.getAllMoviesSortByRating();
    }

    @GetMapping(value = "/{name}")
    public List<Movie> getMovieByName(@PathVariable String name){
        return movieService.getByName(name);
    }

    @PostMapping
    public ResponseEntity<String> addOne(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{idMovie}")
    public ResponseEntity<String> deleteOne(@PathVariable Integer idMovie){
        movieService.deleteMovie(idMovie);
        return new ResponseEntity<>("deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping(params = {"page","size"})
    public Page<Movie> indPaginated(@RequestParam("page") int page,
                                    @RequestParam("size") int size) {
     return movieService.findPaginated(page, size);
    }
} 
