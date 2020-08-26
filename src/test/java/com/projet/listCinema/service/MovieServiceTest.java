package com.projet.listCinema.service;

import com.projet.listCinema.entity.Movie;
import com.projet.listCinema.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Captor
     private ArgumentCaptor<Movie> movieArgumentCaptor;

    @ParameterizedTest
    @MethodSource("provideMoviesToAdd")
    public void  should_add_movie(Movie movie){
        movieService.addMovie(movie);
        Mockito.verify(movieRepository,Mockito.times(1)).save(Mockito.any(Movie.class));
    }

    @Test
    public void should_delete_movie(){
        movieService.deleteMovie(1);
        Mockito.verify(movieRepository,Mockito.times(1)).deleteById(1);
    }

    private static Stream<Arguments> provideMoviesToAdd(){
        return Stream.of(
                Arguments.of(Movie.builder().build()),
                Arguments.of(Movie.builder().id(1).build()),
                Arguments.of(Movie.builder().id(2).name("movie_name").build()),
                Arguments.of(Movie.builder().id(3).name("movie_name").rating(8).opening("opening").build())
        );
    }

    @Test
    public void Should_return_Movies(){

        Mockito.when(movieRepository.findAll()).thenReturn(List.of(Movie.builder().build()));
        Mockito.verify(movieRepository,Mockito.times(1)).deleteById(1);
        Assertions.assertThat(movieService.getAllMovies()).hasOnlyElementsOfType(Movie.class);
    }
/*
    @Test
    public void Should_return_MoviesSortByRating(){

        Mockito.when(movieRepository.findByOrderByRatingAsc()).thenReturn(List.of(Movie.builder().build()));
        Assert.isTrue((movieService.getAllMoviesSortByRating().get(0).getRating() >= movieService.getAllMoviesSortByRating().get(1).getRating()), "true");
    }*/



    @Test
    public void Should_return_NameDeMovies(){

        Mockito.when(movieRepository.findByName("1917")).thenReturn(List.of(Movie.builder().build()));
        Assertions.assertThat(movieService.getByName("1917")).isNotEmpty();

    }


}
