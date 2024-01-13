package pl.coderslab.spring01hibernateonljeew24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernateonljeew24.dao.BookDao;
import pl.coderslab.spring01hibernateonljeew24.dao.PublisherDao;
import pl.coderslab.spring01hibernateonljeew24.entity.Book;
import pl.coderslab.spring01hibernateonljeew24.entity.Publisher;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
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

    @GetMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString(); // todo handle book NOT found exception
    }
}
