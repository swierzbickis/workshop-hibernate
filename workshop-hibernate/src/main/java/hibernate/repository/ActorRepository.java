package hibernate.repository;

import hibernate.model.Actor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActorRepository {

    Actor save(Actor actor);

    Optional<Actor> findById(final UUID id);

    List<Actor> findBornAfterYear(int yearOfBirth);

    List<Actor> findAllWithLastNameEndsWith(final String suffix);

}
