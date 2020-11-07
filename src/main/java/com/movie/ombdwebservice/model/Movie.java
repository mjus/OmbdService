package com.movie.ombdwebservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "MOVIE")
public class Movie implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private String year;

    @Column(name = "rating")
    private Double rating;

    public Movie() {
    }

    public Movie(String id, String title, String year) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = Double.parseDouble(String.format("%.1f", Math.random() * (10 - 0) + 0));
    }
}
