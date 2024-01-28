package pl.coderslab.spring01hibernateonljeew24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    // Zadanie 1 - rozwiązywane z wykładowcą
    //W repozytorium dla klasy Book utwórz metody pobierające dane za pomocą zapytań Query:
    //metodę wyszukującą książki dla zadanego tytułu.
    @Query("SELECT e FROM Book e WHERE e.title LIKE %?1%")
    List<Book> myFindByTitlePart(String titlePart);
    //metodę wyszukującą książki dla zadanej kategorii
    @Query("SELECT e FROM Book e WHERE e.category = :categ")
    List<Book> myFindByCategory(@Param("categ") Category category);
    List<Book> findAllByCategoryIsNull();
    // more examples (not tested in this project)
    // #1
    //     @Query("SELECT distinct b FROM Book b LEFT JOIN FETCH b.authors WHERE b.category.id = :catId ")
    //    List<Book> findAllByCategoryIdWithAuthors(@Param("catId") Long categoryId);
    // #2
    //     @Query(value = "SELECT * FROM books WHERE category_id = ?1 ORDER BY title LIMIT 1", nativeQuery = true)
    //    Book queryFirstInCategory(long catId);
    // #3
    //     @Query(value = "SELECT id, title FROM books WHERE id = ?1", nativeQuery = true)
    //    List<Object[]> queryPart(long id);
}
