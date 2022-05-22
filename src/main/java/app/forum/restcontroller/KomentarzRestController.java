package app.forum.restcontroller;

import app.forum.service.KomentarzService;
import app.forum.utils.DodajKomentarzRequest;
import app.forum.utils.OdpowiedzBazowa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/api/komentarz")
public class KomentarzRestController {

    @Autowired
    private KomentarzService odpowiedzService;

    @PostMapping("/dodajKomentarz")
    public OdpowiedzBazowa dodajPost(DodajKomentarzRequest request, HttpServletResponse response) throws IOException {

        OdpowiedzBazowa odp = odpowiedzService.dodajKomentarz(request);
        if(odp.isSukces()) response.sendRedirect("/post/"+request.postId);
        return odp;
    }
}
