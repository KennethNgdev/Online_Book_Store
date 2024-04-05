package hkmu.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
    @GetMapping(value = {"","/"})
    public String list(){
        return "book/list";
    }
}
