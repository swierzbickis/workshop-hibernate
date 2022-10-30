package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

    @Column(name = "movie_title")
    private String title;

}
