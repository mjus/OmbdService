package com.movie.ombdwebservice.service.tesicnor;

import com.movie.ombdwebservice.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TesicnorMovieService {

    void saveAll(List<Movie> list);
}
