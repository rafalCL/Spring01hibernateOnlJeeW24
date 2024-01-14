package pl.coderslab.spring01hibernateonljeew24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/addFormBind")
    public String addFormBind(Model m) {
        m.addAttribute("person", new Person());
        return "/person/addFormBind";
    }

    @PostMapping("/addFormBind")
    @ResponseBody
    public String postAddFormBind(@ModelAttribute Person person) {
        return person.toString();
    }

// below binding with already prefilled object
    @GetMapping("/edit")
    public String editPerson(Model m) {
        m.addAttribute("person", new Person("somelogin", "somepassword", "someemail"));
        return "/person/addFormBind";
    }

    @PostMapping("/edit")
    @ResponseBody
    public String postEdit(@ModelAttribute Person person) {
        return person.toString();
    }
}
