package hibernate.repository;

import hibernate.model.Genre;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GenreRepositoryImpl implements GenreRepository {

    private final Session session;

    public GenreRepositoryImpl(Session session) {
        this.session = session;
    }

    public List<Genre> findAll() {
        return session.createQuery("from genres", Genre.class).getResultList();
    }

    public Optional<Genre> findById(final UUID id) {
        return Optional.ofNullable(session.find(Genre.class, id));
    }

    public List<Genre> findAllByName(String name) {
        return session.createQuery("SELECT g FROM genres WHERE name = :name", Genre.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public Genre save(Genre genre) {
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            session.persist(genre);
            transaction.commit();
            return genre;
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public void remove(final Genre genre) {
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            session.remove(genre);
            transaction.commit();
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

