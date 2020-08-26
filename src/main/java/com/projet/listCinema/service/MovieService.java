package com.projet.listCinema.service;

import com.projet.listCinema.entity.Movie;
import com.projet.listCinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies(){
        return  movieRepository.findAll();
    }

    public List<Movie> getAllMoviesSortByRating(){
        return  movieRepository.findByOrderByRatingAsc();
    }

    public List<Movie> getByName(String name){
        return  movieRepository.findByName(name);
    }

    public void addMovie(Movie movie){
        movieRepository.save(movie);
    }
    public void deleteMovie(int idMovie){
        movieRepository.deleteById(idMovie);
    }

    public Page<Movie> findPaginated(int page, int  size){
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable);
    }
}
