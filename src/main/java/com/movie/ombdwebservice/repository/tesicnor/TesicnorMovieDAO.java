package com.movie.ombdwebservice.repository.tesicnor;

import com.movie.ombdwebservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TesicnorMovieDAO extends JpaRepository<Movie, Long> {

    @Override
    <S extends Movie> List<S> saveAll(Iterable<S> iterable);
}
