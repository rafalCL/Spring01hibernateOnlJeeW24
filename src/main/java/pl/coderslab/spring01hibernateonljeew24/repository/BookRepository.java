package pl.coderslab.spring01hibernateonljeew24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.spring01hibernateonljeew24.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByRatingGreaterThanEqual(int minRating);
}
