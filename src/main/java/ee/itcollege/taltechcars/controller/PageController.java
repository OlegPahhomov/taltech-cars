package ee.itcollege.taltechcars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class PageController {

    @GetMapping(value = "first")
    public String first() {
        return "pages/first.html";
    }

    @GetMapping(value = "second")
    public String second(Model model) {
        model.addAttribute("lists", Arrays.asList(1, 2, 3));
        return "second";
    }
}
