package pl.coderslab.spring01hibernateonljeew24.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.spring01hibernateonljeew24.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Publisher create(Publisher entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public List<Publisher> findAll() {
        return entityManager.createQuery("SELECT p FROM Publisher p").getResultList();
    }
}
