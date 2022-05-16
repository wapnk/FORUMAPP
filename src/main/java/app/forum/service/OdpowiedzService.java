package app.forum.service;

import app.forum.model.Odpowiedz;
import app.forum.model.Post;
import app.forum.repository.OdpowiedzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OdpowiedzService {

    @Autowired
    private OdpowiedzRepository odpowiedzRepository;
    @Autowired
    private PostService postService;
    public void dodajOdpowiedz(Odpowiedz odpowiedz, Long post_id) {

        Post post=postService.pobierzPoId(post_id);
        odpowiedz.setPost(post);
        post.getOdpowiedzi().add(odpowiedz);
        odpowiedzRepository.save(odpowiedz);
    }
}
