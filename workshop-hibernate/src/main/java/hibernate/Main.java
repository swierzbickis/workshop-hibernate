package hibernate;

import hibernate.model.Actor;
import hibernate.model.Genre;
import hibernate.model.Movie;
import hibernate.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class Main {

    private static final String PERSISTENCE_UNIT_NAME = "TestPersistence";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = factory.createEntityManager();

        invokeGenreRepository(entityManager);
        invokeActorRepository(entityManager);
        invokeMovieRepository(entityManager);
    }

    private static void invokeGenreRepository(EntityManager entityManager) {
        final GenreRepository genreRepository = new GenreRepositoryImpl(entityManager);

        Genre genre = new Genre("Action", null);
        genreRepository.save(genre);
        System.out.println(genreRepository.findAll().size());
        genreRepository.remove(genre);
        System.out.println(genreRepository.findAll().size());
    }

    private static void invokeActorRepository(EntityManager entityManager) {
        final ActorRepository actorRepository = new ActorRepositoryImpl(entityManager);

        Actor actor = new Actor("John", "Wayne", 1907, null);
        actorRepository.save(actor);
        System.out.println(actorRepository.findBornAfterYear(1906));
        System.out.println(actorRepository.findBornAfterYear(1907));
        System.out.println(actorRepository.findAllWithLastNameEndsWith("ayne"));
    }

    private static void invokeMovieRepository(EntityManager entityManager) {
        final MovieRepository movieRepository = new MovieRepositoryImpl(entityManager);
        final GenreRepository genreRepository = new GenreRepositoryImpl(entityManager);
        final ActorRepository actorRepository = new ActorRepositoryImpl(entityManager);

        Genre genre = new Genre("Thriller", null);
        genreRepository.save(genre);
        Actor actor = new Actor("Marlon", "Brando", 1920, null);
        actorRepository.save(actor);
        Movie movie = new Movie("Godfather", 1974, List.of(actor), genre);
        movieRepository.save(movie);

        System.out.println(movieRepository.findByTitle("Godfather"));
        System.out.println(movieRepository.findAllMoviesWithAllActors());
    }


}
