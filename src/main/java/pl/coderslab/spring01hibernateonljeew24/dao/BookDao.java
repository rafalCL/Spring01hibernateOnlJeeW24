package pl.coderslab.spring01hibernateonljeew24.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.spring01hibernateonljeew24.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }
}
