package jpa.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository
public class CustomEntityManagerSupportImpl<T, ID> implements CustomEntityManagerSupport<T, ID> {
    @PersistenceContext
    private final EntityManager entityManager;

    public CustomEntityManagerSupportImpl(
            final EntityManager entityManager
    ) {
        this.entityManager = entityManager;
    }

    @Override
    public void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void clear() {
        entityManager.clear();
    }
}
