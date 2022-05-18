package app.forum.restcontroller;

import app.forum.dto.PostDTO;
import app.forum.model.Post;
import app.forum.service.DzialService;
import app.forum.service.PostService;
import app.forum.utils.DodajPostRequest;
import app.forum.utils.OdpowiedzBazowa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostRestController {

    @Autowired
    private PostService postyService;
    @Autowired
    private DzialService dzialService;

    @GetMapping("/pobierzPosty")
    public List<Post> pobierzPosty() {

        postyService.pobierzPosty();
        return null;
    }

    @GetMapping("{id}")
    public Post pobierzPost(@PathVariable Long id) {
        Post post = postyService.pobierzPoId(id);
//        PostDTO postDTO=PostDTO.builder()
//                .id(44L)
//                .nazwa("nazwa")
//                .tresc("tresc")
//                .dataZalozenia(LocalDateTime.now())
//                .odpowiedzi(post.getOdpowiedzi())
//                .build();
        return post;
    }

    @PostMapping("/dodajPost")
    public OdpowiedzBazowa dodajPost(DodajPostRequest request, HttpServletResponse response) throws IOException {

        OdpowiedzBazowa odp = postyService.dodajPost(request);
        response.sendRedirect("http://www.localhost:8080/glowna");
        return odp;
    }

}
