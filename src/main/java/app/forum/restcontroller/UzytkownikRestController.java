package app.forum.restcontroller;

import app.forum.model.Uzytkownik;
import app.forum.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/konto")
public class UzytkownikRestController {

    @Autowired
    private UzytkownikService uzytkownikService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("zarejestruj")
    public String zarejestruj(Uzytkownik uzytkownik){

        uzytkownik.setHaslo(passwordEncoder.encode(uzytkownik.getPassword()));
        uzytkownikService.zapisz(uzytkownik);
        return "ok";
    }
}
