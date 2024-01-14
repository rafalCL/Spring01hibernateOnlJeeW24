package pl.coderslab.spring01hibernateonljeew24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernateonljeew24.dao.AuthorDao;
import pl.coderslab.spring01hibernateonljeew24.entity.Author;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @PostMapping("/create")
    @ResponseBody
    public String create(@RequestParam String name) {
        Author entity = new Author(name);
        entity = authorDao.create(entity);
        return entity.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable long id) {
        Author entity = authorDao.findById(id);
        return entity.toString(); // todo handle book NOT found exception
    }

    @GetMapping("/{id}/withBooks")
    @ResponseBody
    public String getByIdWithBooks(@PathVariable long id) {
        Author entity = authorDao.findByIdWithBooks(id);
        return String.format("<div>author: %s</div>\r\n<div>his books: %s</div>",
                entity.toString(),
                entity.getBooks()); // todo handle book NOT found exception
    }
}
