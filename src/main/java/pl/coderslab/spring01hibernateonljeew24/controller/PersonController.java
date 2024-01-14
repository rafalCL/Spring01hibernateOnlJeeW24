package pl.coderslab.spring01hibernateonljeew24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.spring01hibernateonljeew24.entity.Person;

@Controller
@RequestMapping("/person")
public class PersonController {
    @GetMapping("/addForm")
    public String addForm() {
        return "/person/addForm";
    }

    @PostMapping("/addForm")
    @ResponseBody
    public String postAddForm(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String email
    ) {
        Person person = new Person(login, password, email);
        // todo save to db
        return person.toString();
    }
}
