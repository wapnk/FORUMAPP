package app.forum.service;

import app.forum.model.Dzial;
import app.forum.model.Post;
import app.forum.model.Uzytkownik;
import app.forum.repository.PostRepository;
import app.forum.utils.DodajPostRequest;
import app.forum.utils.OdpowiedzBazowa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private DzialService dzialService;

    @Autowired
    private UzytkownikService uzytkownikService;

    public List<Post> pobierzPosty() {
        return postRepository.getAll();
    }


    public OdpowiedzBazowa dodajPost(DodajPostRequest request) {

        try {
            validujRequest(request);
            Dzial dzial = dzialService.pobierzDzialPoId(request.getDzialId());
            if (dzial == null) {
                throw new NullPointerException("Brak takiego dzialu");
            }
            Post post = stworzPost(request, dzial);
            post = postRepository.save(post);
            return OdpowiedzBazowa.builder()
                    .sukces(true)
                    .komunikat("Post dodany @/post" + post.getId())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return OdpowiedzBazowa.builder()
                    .sukces(false)
                    .komunikat(e.getMessage())
                    .build();
        }
    }

    private Post stworzPost(DodajPostRequest request, Dzial dzial) {

        Uzytkownik u = uzytkownikService.zwrocZalogowanego();
        Post post = Post.builder()
                .dzial(dzial)
                .dataZalozenia(LocalDateTime.now())
                .nazwa(request.getNazwa())
                .tresc(request.getTresc())
                .zalozonePrzez(u)
                .build();
        return post;
    }

    private void validujRequest(DodajPostRequest request) {
        if (request.getDzialId() == null || request.getTresc() == null || request.getTresc().isEmpty()) {
            throw new IllegalArgumentException("Nieprawidlowe parametry zadania");
        } else if (request.getNazwa() == null || request.getNazwa().isEmpty()) {
            throw new IllegalArgumentException("Nieprawidlowe parametry zadania");
        }

    }


    public Post pobierzPoId(Long id) {
        return postRepository.getById(id);
    }

    public List<Post> zapiszWszystkie(List<Post> posty) {
        return postRepository.saveAll(posty);
    }
}
