package hibernate.repository;

import hibernate.model.Movie;
import org.hibernate.Session;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MovieRepositoryImpl implements MovieRepository {

    private final Session session;

    public MovieRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Movie save(Movie movie) {
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            session.persist(movie);
            transaction.commit();
            return movie;
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public void remove(Movie movie) {
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            session.remove(movie);
            transaction.commit();
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return session.createQuery("SELECT m FROM movies m WHERE m.title = :title", Movie.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public Optional<Movie> findById(UUID id) {
        return Optional.ofNullable(session.find(Movie.class, id));
    }

    @Override
    public List<Movie> findAll() {
        return session.createQuery("from movies", Movie.class).getResultList();
    }

    @Override
    public List<Movie> findAllMoviesWithAllActors() {
        return session.createQuery("SELECT m FROM movies m LEFT JOIN fetch m.actors", Movie.class)
                .getResultList();
    }
}
