package app.forum.restcontroller;

import app.forum.service.BanService;
import app.forum.utils.DodajBanaRequest;
import app.forum.utils.OdpowiedzBazowa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/ban")
public class BanRestController {

    @Autowired
    BanService banService;


    @PostMapping("/dodajBana")
    public OdpowiedzBazowa dodajBana(DodajBanaRequest request, HttpServletResponse response) throws IOException {

        OdpowiedzBazowa odp = banService.dodajBana(request);
        if(odp.isSukces()) response.sendRedirect("/glowna");
        return odp;

    }
}
