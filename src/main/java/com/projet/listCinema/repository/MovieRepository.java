package com.projet.listCinema.repository;

import com.projet.listCinema.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie , Integer> {

    List<Movie> findByOrderByRatingAsc();
    List<Movie> findByName(String name);

    Page<Movie> findAll(Pageable page);
}
