package pl.coderslab.spring01hibernateonljeew24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spring01hibernateonljeew24.entity.Person2;
import pl.coderslab.spring01hibernateonljeew24.repository.Person2Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/person2", produces = "text/html;charset=utf-8")
@ResponseBody
public class Person2Controller {
    private final Person2Repository repo;

    public Person2Controller(Person2Repository repo) {
        this.repo = repo;
    }

    @GetMapping("/create")
    public String create(@RequestParam String login) {
        Person2 person2 = new Person2(login, "pwd", "email-" + UUID.randomUUID().toString() + "@mail.pl");
        repo.save(person2);
        return "saved: " + person2.toString();
    }

    @GetMapping("")
    public String all() {
        String BR = "<br>\r\n";
        long count = repo.count();
        List<Person2> all = repo.findAll();
        String html = "count: " + count + BR;
        String entities = all.stream()
                .map(Person2::toString)
                .collect(Collectors.joining(BR));
        return html + entities;
    }
}
