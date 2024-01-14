package pl.coderslab.spring01hibernateonljeew24.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.spring01hibernateonljeew24.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Book create(Book entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b").getResultList();
    }

    public List<Book> findByRatingGt(int minRating) {
        Query q = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating >= :rating");
        q.setParameter("rating", minRating);
        return q.getResultList();
    }
}
