package pl.coderslab.spring01hibernateonljeew24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernateonljeew24.dao.AuthorDao;
import pl.coderslab.spring01hibernateonljeew24.dao.PublisherDao;
import pl.coderslab.spring01hibernateonljeew24.entity.Author;
import pl.coderslab.spring01hibernateonljeew24.entity.Book;
import pl.coderslab.spring01hibernateonljeew24.entity.Publisher;
import pl.coderslab.spring01hibernateonljeew24.repository.BookRepository;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bookRepo")
public class BookWithRepositoryController {
    private final BookRepository bookRepository;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookWithRepositoryController(BookRepository bookRepository, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookRepository = bookRepository;
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
        b = bookRepository.save(b);
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
        b = bookRepository.save(b);
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
        b = bookRepository.save(b);
        return b.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null) {
            return "book not found for id=" + id;
        }
        return book.toString();
    }

    @GetMapping("")
    public String getAll(Model m) {
        List<Book> books = bookRepository.findAll();
        m.addAttribute("listHeader", "All Books");
        m.addAttribute("books", books);
        return "/book/list";
    }

    @GetMapping("/good")
    public String good(Model m) {
        List<Book> books = bookRepository.findByRatingGreaterThanEqual(8);
        m.addAttribute("listHeader", "Only Books with Rating Greather than or equal 8");
        m.addAttribute("books", books);
        return "/book/list";
    }

    @GetMapping("/withMinRating/{minRating}")
    @ResponseBody
    public String getByRatingGt(@PathVariable int minRating) {
        List<Book> books = bookRepository.findByRatingGreaterThanEqual(minRating);
        return books.toString();
    }

    @GetMapping("/addForm")
    public String addForm(Model m) {
        m.addAttribute("book", new Book());
        m.addAttribute("formHeader", "Add Book");
        return "/book/addOrEditForm";
    }

    @PostMapping("/addForm")
    public String postAddForm(@Valid @ModelAttribute Book book, BindingResult br) {
        if(br.hasErrors()) {
            return "/book/addOrEditForm";
        }
        bookRepository.save(book);
        return "redirect:/bookRepo";
    }

    @GetMapping("/editForm")
    public String editForm(Model m, @RequestParam long id) {
        m.addAttribute("book", bookRepository.findById(id).orElse(null));
        m.addAttribute("formHeader", "Edit Book");
        return "/book/addOrEditForm";
    }

    @PostMapping("/editForm")
    public String postEditForm(@Valid @ModelAttribute Book book, BindingResult br) {
        if(br.hasErrors()) {
            return "/book/addOrEditForm";
        }
        bookRepository.save(book);
        return "redirect:/bookRepo/" + book.getId();
    }

    @GetMapping("/byTitle")
    public String getByTitlePart(@RequestParam String title, Model m) {
        List<Book> books = bookRepository.findAllByTitleContainingIgnoreCase(title);
        m.addAttribute("listHeader", "All Books where title contains phrase: " + title);
        m.addAttribute("books", books);
        return "/book/list";
    }

    @GetMapping("/byPublisher")
    public String byPublisher(@RequestParam long id, Model m) {
        Publisher publisher = publisherDao.findById(id);
        List<Book> books = bookRepository.findAllByPublisher(publisher);
        m.addAttribute("listHeader", "All Books where publisher is: " + publisher.getName());
        m.addAttribute("books", books);
        return "/book/list";
    }

    @GetMapping("/byPublisherId")
    public String byPublisherId(@RequestParam long id, Model m) {
        List<Book> books = bookRepository.findAllByPublisherId(id);
        m.addAttribute("listHeader", "All Books where publisher id is: " + id);
        m.addAttribute("books", books);
        return "/book/list";
    }

    @GetMapping("/byCategoryId")
    public String byCategoryId(@RequestParam(required = false) Long id, Model m) {
        List<Book> books = bookRepository.findAllByCategoryId(id);
        m.addAttribute("listHeader", "All Books where category id is: " + id);
        m.addAttribute("books", books);
        return "/book/list";
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
