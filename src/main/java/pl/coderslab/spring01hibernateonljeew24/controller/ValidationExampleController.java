package pl.coderslab.spring01hibernateonljeew24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernateonljeew24.entity.Book;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/validation", produces = "text/html;charset=utf-8")
@ResponseBody
public class ValidationExampleController {
    private final Validator validator;

    public ValidationExampleController(Validator validator) {
        this.validator = validator;
    }

    @GetMapping("/example1")
    public String example1() {
        Book book = new Book();
        book.setRating(42);
        Set<ConstraintViolation<Book>> violations = this.validator.validate(book);
        final String html = violations.stream()
                .map(cv -> String.format("%s : %s", cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.joining("<br>\r\n"));
        return html;
    }
}
