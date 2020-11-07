package com.movie.ombdwebservice.service.tesicnor;

import com.movie.ombdwebservice.model.Movie;
import com.movie.ombdwebservice.repository.tesicnor.TesicnorMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesicnorMovieServiceImpl {

    @Autowired
    TesicnorMovieDAO tesicnorMovieDAO;

    public void saveAll(List<Movie> list) {
        tesicnorMovieDAO.saveAll(list);
    }
}
