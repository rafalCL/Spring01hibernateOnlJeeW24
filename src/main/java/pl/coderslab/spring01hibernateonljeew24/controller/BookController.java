package pl.coderslab.spring01hibernateonljeew24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernateonljeew24.dao.AuthorDao;
import pl.coderslab.spring01hibernateonljeew24.dao.BookDao;
import pl.coderslab.spring01hibernateonljeew24.dao.PublisherDao;
import pl.coderslab.spring01hibernateonljeew24.entity.Author;
import pl.coderslab.spring01hibernateonljeew24.entity.Book;
import pl.coderslab.spring01hibernateonljeew24.entity.Publisher;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @PostMapping("/create")
    @ResponseBody
    public String createBook(@RequestParam String title,
                             @RequestParam int rating,
                             @RequestParam String description
                             ) {
        Book b = new Book(title, rating, description);
        b = bookDao.create(b);
        return b.toString();
    }

    @PostMapping("/createWithPublisher")
    @ResponseBody
    public String createBookWithPublisher(@RequestParam String title,
                             @RequestParam int rating,
                             @RequestParam String description,
                             @RequestParam String publisherName
    ) {
        Publisher publisher = new Publisher(publisherName);
        publisher = publisherDao.create(publisher);
        Book b = new Book(title, rating, description);
        b.setPublisher(publisher);
        b = bookDao.create(b);
        return b.toString();
    }

    @PostMapping("/createWithAuthors")
    @ResponseBody
    public String createBookWithAuthors(@RequestParam String title,
                                          @RequestParam int rating,
                                          @RequestParam String description,
                                          @RequestParam String authorsCsv
    ) {
        List<Author> savedAuthors = Arrays.stream(authorsCsv.split(","))
                .map(Author::new)//                .map(authorNameStr -> new Author(authorNameStr))
                .map(authorDao::create)//                .map(author -> authorDao.create(author))
                .collect(Collectors.toList());

        Book b = new Book(title, rating, description);
        b.setAuthors(savedAuthors);
        b = bookDao.create(b);
        return b.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString(); // todo handle book NOT found exception
    }

    @GetMapping("")
    @ResponseBody
    public String getAll() {
        List<Book> all = bookDao.findAll();
        return all.toString();
    }

    @GetMapping("/withMinRating/{minRating}")
    @ResponseBody
    public String getByRatingGt(@PathVariable int minRating) {
        List<Book> books = bookDao.findByRatingGt(minRating);
        return books.toString();
    }

    @GetMapping("/addForm")
    public String addForm(Model m) {
        m.addAttribute("book", new Book());
        return "/book/addForm";
    }

    @PostMapping("/addForm")
    @ResponseBody
    public String postAddForm(@ModelAttribute Book book) {
        bookDao.create(book);
        return "saved book: " + book.toString();
    }

    @ModelAttribute(name = "publishers")
    public List<Publisher> publishers() {
        return publisherDao.findAll();
    }

    @ModelAttribute(name = "authors")
    public List<Author> authors() {
        return authorDao.findAll();
    }
}
