package hibernate;

import hibernate.model.Actor;
import hibernate.model.Genre;
import hibernate.model.Movie;
import hibernate.repository.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();

        invokeGenreRepository(sessionFactory.openSession());
        invokeActorRepository(sessionFactory.openSession());
        invokeMovieRepository(sessionFactory.openSession());
    }

    private static void invokeGenreRepository(Session session) {
        final GenreRepository genreRepository = new GenreRepositoryImpl(session);

        Genre genre = new Genre("Action", null);
        genreRepository.save(genre);
        System.out.println(genreRepository.findAll().size());
        genreRepository.remove(genre);
        System.out.println(genreRepository.findAll().size());
    }

    private static void invokeActorRepository(Session session) {
        final ActorRepository actorRepository = new ActorRepositoryImpl(session);

        Actor actor = new Actor("John", "Wayne", 1907, null);
        actorRepository.save(actor);
        System.out.println(actorRepository.findBornAfterYear(1906));
        System.out.println(actorRepository.findBornAfterYear(1907));
        System.out.println(actorRepository.findAllWithLastNameEndsWith("ayne"));
    }

    private static void invokeMovieRepository(Session session) {
        final MovieRepository movieRepository = new MovieRepositoryImpl(session);
        final GenreRepository genreRepository = new GenreRepositoryImpl(session);
        final ActorRepository actorRepository = new ActorRepositoryImpl(session);

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
