package pl.coderslab.spring01hibernateonljeew24.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Optional;

// Dla encji Book ustaw następujące ograniczenia:
//title - minimum 5 znaków
//rating - w przedziale 1 do 10
//description - maksymalnie 600 znaków
//publisher - pole wymagane
//Rozbuduj encję o pole:
//pages - większe od 1

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min=4)
    private String title;
//    @Min(1)
//    @Max(10)
    @Range(min = 1, max = 10)
    private int rating;
    @Size(max = 600)
    private String description;
    @ManyToOne
    @NotNull
    private Publisher publisher;
    @ManyToMany
    @NotEmpty
    private List<Author> authors;
    @Min(2)
    private int pages;
    @ManyToOne
    private Category category;

    public Book() {

    }

    public Book(String title, int rating, String description) {
        this.title = title;
        this.rating = rating;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", publisher=" + publisher.getName() +
//                ", authors=" + authors +
                ", pages=" + pages +
                ", category=" + Optional.ofNullable(category).map(Category::getName).orElse("null") +
                '}';
    }
}
