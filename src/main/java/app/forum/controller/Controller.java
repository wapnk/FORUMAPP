package app.forum.controller;

import app.forum.model.Dzial;
import app.forum.model.Post;
import app.forum.model.Uzytkownik;
import app.forum.restcontroller.DzialRestController;
import app.forum.restcontroller.PostRestController;
import app.forum.restcontroller.UzytkownikRestController;
import app.forum.service.BanService;
import app.forum.service.UzytkownikService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@org.springframework.stereotype.Controller
@Log4j2
@RequestMapping("/")
public class Controller {

    @Autowired
    PostRestController postRestController;
    @Autowired
    DzialRestController dzialRestController;
    @Autowired
    UzytkownikService uzytkownikService;

    @Autowired
    BanService banService;
    @Autowired
    UzytkownikRestController uzytkownikRestController;

    @GetMapping("/glowna")
    public String glowna(Model model) {
        Uzytkownik u = uzytkownikService.zwrocZalogowanego();
        String nazwa = u.getNazwa();
        List<Dzial> dzialy = dzialRestController.pobierzDzialy();
        model.addAttribute("dzialy", dzialy);
        model.addAttribute("nazwa", nazwa);
        return "glowna";
    }

    @GetMapping("/post/{id}")
    public String pobierzPost(@PathVariable Long id, Model model) {
        Post post = postRestController.pobierzPost(id);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/post/nowypost")
    public String nowyPost(Model model, @RequestParam(name = "dzialId") Long dzialId, HttpServletRequest request) {
        
        model.addAttribute("dzialId", dzialId);
        return "nowy-post";
    }

    @GetMapping("/zarejestruj")
    public String zarejestruj(Model model) {

        return "zarejestruj";
    }

    @GetMapping("/uzytkownik/{id}")
    public String zwrocUzytkownika(@PathVariable Long id, Model model) {

        Uzytkownik u = uzytkownikRestController.zwrocUzytkownikaPoId(id);
        model.addAttribute("u", u);
        return "uzytkownik";
    }

    @GetMapping("/admin")
    public String panelAdmina(Model model) {

        List<Uzytkownik> usrs = uzytkownikService.getAll();

        model.addAttribute("usrs", usrs);
        return "admin";
    }

    @GetMapping("/admin/zbanuj/{id}")
    public String zbanuj(@PathVariable Long id, Model model) {

        model.addAttribute("id", id);
        return "zbanuj";
    }

}