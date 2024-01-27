package pl.coderslab.spring01hibernateonljeew24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernateonljeew24.entity.Category;
import pl.coderslab.spring01hibernateonljeew24.repository.CategoryRepository;

@Controller
@RequestMapping(value = "/category", produces = "text/html;charset=utf-8")
@ResponseBody
public class CategoryController {
    private final CategoryRepository repo;

    public CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/create")
    @ResponseBody
    public String create(@RequestParam String name) {
        Category category = new Category(name);
        repo.save(category);
        return "saved: " + category.toString();
    }
}
