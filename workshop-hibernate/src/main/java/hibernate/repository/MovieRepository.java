package hibernate.repository;

import hibernate.model.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository {

    Movie save(Movie movie);

    void remove(final Movie movie);

    List<Movie> findByTitle(String title);

    Optional<Movie> findById(final UUID id);

    List<Movie> findAll();

    List<Movie> findAllMoviesWithAllActors();

}
