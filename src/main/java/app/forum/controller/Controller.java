package app.forum.controller;

import app.forum.model.Dzial;
import app.forum.model.Post;
import app.forum.restcontroller.DzialRestController;
import app.forum.restcontroller.PostRestController;
import app.forum.service.DzialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    PostRestController postRestController;

    @Autowired
    DzialRestController dzialRestController;

    @GetMapping("/glowna")
    public String glowna(Model model) {

        List<Dzial> dzialy = dzialRestController.pobierzDzialy();
        model.addAttribute("dzialy", dzialy);
        return "glowna";
    }

    @GetMapping("/post/{id}")
    public String pobierzPost(@PathVariable Long id, Model model) {

        Post post = postRestController.pobierzPost(id);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/post/nowypost")
    public String nowyPost(Model model, @RequestParam(name = "dzial_id") Long dzial_id) {
        model.addAttribute("dzial_id",dzial_id);
        System.out.println(dzial_id);
        return "nowy-post";
    }
}
