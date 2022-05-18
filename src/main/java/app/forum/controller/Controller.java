package app.forum.controller;

import app.forum.model.Dzial;
import app.forum.model.Odpowiedz;
import app.forum.model.Post;
import app.forum.model.Uzytkownik;
import app.forum.restcontroller.DzialRestController;
import app.forum.restcontroller.PostRestController;
import app.forum.service.DzialService;
import app.forum.service.UzytkownikService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Controller
//@RequestMapping("forum")
@Log4j2
public class Controller {

    @Autowired
    PostRestController postRestController;
    @Autowired
    DzialRestController dzialRestController;

    @Autowired
    UzytkownikService uzytkownikService;

    @GetMapping("/glowna")
    public String glowna(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
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
        List<Odpowiedz> odpowiedzi = post.getOdpowiedzi();
        Uzytkownik u = post.getZalozonePrzez();
        if (odpowiedzi != null && !odpowiedzi.isEmpty()) {
            odpowiedzi.forEach(o -> log.info(o.toString()));
        } else {
            log.info(odpowiedzi);
        }
        if (u != null) {
            log.info(u.toString());
        } else {
            log.info(u);
        }
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/post/nowypost")
    public String nowyPost(Model model, @RequestParam(name = "dzialId") Long dzialId, HttpServletRequest request) {
        model.addAttribute("dzialId", dzialId);
        return "nowy-post";
    }

    @GetMapping("konto/zarejestruj")
    public String zarejestruj(Model model) {

        return "zarejestruj";
    }
}
