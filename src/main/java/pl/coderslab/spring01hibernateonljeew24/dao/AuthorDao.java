package pl.coderslab.spring01hibernateonljeew24.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.spring01hibernateonljeew24.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthorDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Author create(Author entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }
}
