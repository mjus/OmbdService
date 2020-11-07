package com.movie.ombdwebservice.service.home;

import com.movie.ombdwebservice.model.Movie;

import java.util.List;

public interface MovieService {

    void add(String title, String key);

    List<Movie> findAll();

    void deleteAll();
}
