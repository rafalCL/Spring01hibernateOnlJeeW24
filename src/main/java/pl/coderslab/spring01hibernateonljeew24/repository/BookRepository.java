package pl.coderslab.spring01hibernateonljeew24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.spring01hibernateonljeew24.entity.Book;
import pl.coderslab.spring01hibernateonljeew24.entity.Category;
import pl.coderslab.spring01hibernateonljeew24.entity.Publisher;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByRatingGreaterThanEqual(int minRating);
//    metodę wyszukującą książki dla zadanego tytułu.
    List<Book> findAllByTitleContainingIgnoreCase(String titlePart);
//    metodę wyszukującą książki dla zadanej kategorii
    List<Book> findAllByCategory(Category category);
//    metodę wyszukującą książki dla zadanego publishera
    List<Book> findAllByPublisher(Publisher publisher);
//    metodę wyszukującą książki dla zadanego id kategorii
    List<Book> findAllByCategoryId(Long categoryId);
//    metodę wyszukującą książki dla zadanego id publishera
    List<Book> findAllByPublisherId(long publisherId);
}
