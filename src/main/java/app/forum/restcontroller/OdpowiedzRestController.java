package app.forum.restcontroller;

import app.forum.model.Odpowiedz;
import app.forum.model.Post;
import app.forum.service.OdpowiedzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/api/odpowiedz")
public class OdpowiedzRestController {

    @Autowired
    private OdpowiedzService odpowiedzService;

    @PostMapping("/dodajOdpowiedz")
    public void dodajPost(Odpowiedz odpowiedz, Long post_id, HttpServletResponse response) throws IOException {

        odpowiedzService.dodajOdpowiedz(odpowiedz,post_id);
        response.sendRedirect("http://www.localhost:8080/post/"+post_id);
    }
}
