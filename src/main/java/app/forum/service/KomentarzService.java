package app.forum.service;

import app.forum.exception.BaseException;
import app.forum.model.Komentarz;
import app.forum.model.Post;
import app.forum.model.Uzytkownik;
import app.forum.repository.KomentarzRepository;
import app.forum.utils.DodajKomentarzRequest;
import app.forum.utils.OdpowiedzBazowa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KomentarzService {

    @Autowired
    private KomentarzRepository komentarzRepository;
    @Autowired
    private PostService postService;

    @Autowired
    private UzytkownikService uzytkownikService;

    public OdpowiedzBazowa dodajKomentarz(DodajKomentarzRequest request) {
        OdpowiedzBazowa odp = new OdpowiedzBazowa();
        try {
            validujRequest(request);
            Post post = postService.pobierzPoId(request.getPostId());
            if (post == null) {
                throw new NullPointerException("Taki post nie istnieje");
            }
            if (post.getDataZamkniecia() != null) {
                throw new RuntimeException("Nie mozna dodac komentrza ! Post zostal zamkniety");
            }
            Komentarz komentarz = stworzKomentarz(request, post);
            komentarzRepository.save(komentarz);
            odp.setKomunikat("Udalo sie dodac komentarz");
            odp.setSukces(true);
            return odp;
        } catch (Exception e) {
            e.printStackTrace();
            odp.setSukces(false);
            odp.setKomunikat(e.getMessage());
            throw new BaseException(e.getMessage(), HttpStatus.BAD_REQUEST,odp);
        }
    }

    private Komentarz stworzKomentarz(DodajKomentarzRequest request, Post post) {
        Uzytkownik u = uzytkownikService.zwrocZalogowanego();
        Komentarz k = Komentarz.builder()
                .uzytkownik(u)
                .dataDodania(LocalDateTime.now())
                .tresc(request.getTresc())
                .post(post)
                .build();
        return k;
    }

    private void validujRequest(DodajKomentarzRequest request) {
        if (request.getPostId() == null) {
            throw new IllegalArgumentException("Taki post nie istnieje");
        }
        if (request.getTresc() == null || request.getTresc().isEmpty()) {
            throw new IllegalArgumentException("Komentarz musi zawierac tresc");
        }
    }
}
