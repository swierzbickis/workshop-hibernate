package hibernate.repository;

import hibernate.model.Actor;
import org.hibernate.Session;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ActorRepositoryImpl implements ActorRepository {

    private final Session session;

    public ActorRepositoryImpl(Session session) {
        this.session = session;
    }


    @Override
    public Actor save(Actor actor) {
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            session.persist(actor);
            transaction.commit();
            return actor;
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public Optional<Actor> findById(UUID id) {
        return Optional.ofNullable(session.find(Actor.class, id));
    }

    @Override
    public List<Actor> findBornAfterYear(int yearOfBirth) {
        return session.createQuery("SELECT a FROM actors a WHERE a.yearOfBirth > :year", Actor.class)
                .setParameter("year", yearOfBirth)
                .getResultList();
    }

    public List<Actor> findAllWithLastNameEndsWith(final String suffix) {
        return session.createQuery("SELECT a FROM actors a WHERE a.lastName LIKE :lastName", Actor.class)
                .setParameter("lastName", "%" + suffix)
                .getResultList();
    }
}
