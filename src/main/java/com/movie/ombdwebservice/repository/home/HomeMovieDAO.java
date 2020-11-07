package com.movie.ombdwebservice.repository.home;

import com.movie.ombdwebservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeMovieDAO extends JpaRepository<Movie, Long> {

    @Override
    Movie save(Movie movie);

    @Override
    void deleteAll();

    @Override
    List<Movie> findAll();
}
