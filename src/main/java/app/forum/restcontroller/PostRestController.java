package app.forum.restcontroller;

import app.forum.model.Dzial;
import app.forum.model.Post;
import app.forum.service.DzialService;
import app.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
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
        System.out.println(id);
        System.out.println("met");
        Post post = postyService.pobierzPoId(id);
        System.out.println(post);
        return post;
    }

    @PostMapping("/dodajPost")
    public void dodajPost(Post post, Long dzial_id, HttpServletResponse response) throws IOException {

        postyService.dodajPost(post,dzial_id);
        response.sendRedirect("http://www.localhost:8080/glowna");
    }

}
