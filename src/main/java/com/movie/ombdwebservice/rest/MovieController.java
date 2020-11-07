package com.movie.ombdwebservice.rest;

import com.movie.ombdwebservice.model.Movie;
import com.movie.ombdwebservice.service.tesicnor.TesicnorMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.movie.ombdwebservice.service.home.MovieService;

import java.util.List;

@RestController
@RequestMapping()
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private TesicnorMovieService tesicnorMovieService;

    /*
    @GetMapping("/{name}")
    public void addMovies(@PathVariable(value = "name") String name) {
        String title = name.replaceAll(" ", "+");
        movieService.add(title, "731e41f");
    }
     */
    //get movies from ombd to locar server
    @GetMapping
    public void addMovies() {
        movieService.add("Harry+Potter", "731e41f");
    }

    //send all movies from local server to other server
    @PostMapping
    public void sendAllMovies() {
        List<Movie> listMoviesForSend = movieService.findAll();
        tesicnorMovieService.saveAll(listMoviesForSend);
    }

    //delete all movies from localserver
    @DeleteMapping
    public void deleteAllMovies() {
        movieService.deleteAll();
    }
}