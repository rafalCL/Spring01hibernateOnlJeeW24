package pl.coderslab.spring01hibernateonljeew24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.spring01hibernateonljeew24.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByRatingGreaterThanEqual(int minRating);
//    metodę wyszukującą książki dla zadanego tytułu.
    List<Book> findAllByTitleContainingIgnoreCase(String titlePart);
//    metodę wyszukującą książki dla zadanej kategorii
//    metodę wyszukującą książki dla zadanego id kategorii
}
