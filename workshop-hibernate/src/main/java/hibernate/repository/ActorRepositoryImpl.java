package hibernate.repository;

import hibernate.model.Actor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ActorRepositoryImpl implements ActorRepository {

    private final EntityManager entityManager;

    public ActorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Actor save(Actor actor) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            entityManager.persist(actor);
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
        return Optional.ofNullable(entityManager.find(Actor.class, id));
    }

    @Override
    public List<Actor> findBornAfterYear(int yearOfBirth) {
        return entityManager.createQuery("SELECT a FROM actors a WHERE a.yearOfBirth > :year", Actor.class)
                .setParameter("year", yearOfBirth)
                .getResultList();
    }

    public List<Actor> findAllWithLastNameEndsWith(final String suffix) {
        return entityManager.createQuery("SELECT a FROM actors a WHERE a.lastName LIKE :lastName", Actor.class)
                .setParameter("lastName", "%" + suffix)
                .getResultList();
    }
}
