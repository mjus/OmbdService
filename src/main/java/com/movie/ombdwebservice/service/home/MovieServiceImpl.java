package com.movie.ombdwebservice.service.home;

import com.movie.ombdwebservice.model.Movie;
import com.movie.ombdwebservice.repository.home.HomeMovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private HomeMovieDAO homeMovieDAO;

    @Autowired
    private WEbServiceOmbd wEbServiceOmbd;

    @lombok.SneakyThrows
    @Transactional
    @Override
    public void add(String title, String key) {
        String jsonResponse = wEbServiceOmbd.searchMovieByTitle(title, key);
        JSONObject json = new JSONObject(jsonResponse);
        JSONArray jsonArrays = json.getJSONArray("Search");
        for (int i = 0; i < (jsonArrays.length()); i++) {
            JSONObject json_obj = jsonArrays.getJSONObject(i);
            String titleMovie = json_obj.getString("Title");
            String year = json_obj.getString("Year");
            String imbdID = json_obj.getString("imdbID");
            Movie movie = new Movie(imbdID, titleMovie, year);
            homeMovieDAO.save(movie);
        }
    }

    @Override
    public List<Movie> findAll() {
        return homeMovieDAO.findAll();
    }

    @Override
    public void deleteAll() {
        homeMovieDAO.deleteAll();
    }
}
