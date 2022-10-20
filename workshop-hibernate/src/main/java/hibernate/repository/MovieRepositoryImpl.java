package hibernate.repository;

import hibernate.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MovieRepositoryImpl implements MovieRepository {

    private final EntityManager entityManager;

    public MovieRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Movie save(Movie movie) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            entityManager.persist(movie);
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
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            entityManager.remove(movie);
            transaction.commit();
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return entityManager.createQuery("SELECT m FROM movies m WHERE m.title = :title", Movie.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public Optional<Movie> findById(UUID id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    @Override
    public List<Movie> findAll() {
        return entityManager.createQuery("from movies", Movie.class).getResultList();
    }

    @Override
    public List<Movie> findAllMoviesWithAllActors() {
        return entityManager.createQuery("SELECT m FROM movies m LEFT JOIN fetch m.actors", Movie.class)
                .getResultList();
    }
}
