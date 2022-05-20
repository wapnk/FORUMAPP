package app.forum.restcontroller;

import app.forum.model.Post;
import app.forum.model.Uzytkownik;
import app.forum.service.UzytkownikService;
import app.forum.utils.OdpowiedzBazowa;
import app.forum.utils.RejestrujUzytkownikaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/uzytkownik")
public class UzytkownikRestController {

    @Autowired
    private UzytkownikService uzytkownikService;

    @PostMapping("zarejestruj")
    public OdpowiedzBazowa zarejestruj(RejestrujUzytkownikaRequest request) {
        return uzytkownikService.zarejestrujUzytkownika(request);
    }

    @GetMapping("{id}")
    public Uzytkownik zwrocUzytkownikaPoId(@PathVariable Long id) {

        return uzytkownikService.pobierzPoId(id);
    }
}
