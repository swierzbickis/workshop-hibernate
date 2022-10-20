package hibernate.repository;

import hibernate.model.Genre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreRepository {
    Genre save(Genre genre);

    List<Genre> findAll();

    Optional<Genre> findById(final UUID id);

    List<Genre> findAllByName(String name);

    void remove(final Genre genre);
}
