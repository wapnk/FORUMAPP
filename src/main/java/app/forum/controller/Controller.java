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
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = attr.getRequest();

        log.info("getCreationTime "+attr.getRequest().getSession().getCreationTime());
        log.info("getAttributeNames "+attr.getRequest().getSession().getAttributeNames());
        log.info("getId "+attr.getRequest().getSession().getId());
        log.info("getMaxInactiveInterval "+attr.getRequest().getSession().getMaxInactiveInterval());

        Uzytkownik u = uzytkownikService.zwrocZalogowanego();
        String nazwa = u.getNazwa();
        List<Dzial> dzialy = dzialRestController.pobierzDzialy();
        model.addAttribute("dzialy", dzialy);
        model.addAttribute("nazwa", nazwa);
        return "glowna";
    }

    @GetMapping("/post/{id}")
    public String pobierzPost(@PathVariable Long id, Model model) {
//        SessionAutoConfiguration session = new SessionAutoConfiguration();
        Post post = postRestController.pobierzPost(id);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/post/nowypost")
    public String nowyPost(Model model, @RequestParam(name = "dzial_id") Long dzial_id, HttpServletRequest request) {
        Cookie[] c = request.getCookies();
        model.addAttribute("dzial_id", dzial_id);
        System.out.println(dzial_id);
        return "nowy-post";
    }

    @GetMapping("konto/zarejestruj")
    public String zarejestruj(Model model) {

        return "zarejestruj";
    }

    @GetMapping("konto/zaloguj")
    public String zaloguj(Model model) {

        return "zaloguj";
    }
}
