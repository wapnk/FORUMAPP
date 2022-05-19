package app.forum.service;

import app.forum.model.Post;
import app.forum.repository.DzialRepository;
import app.forum.repository.KomentarzRepository;
import app.forum.repository.PostRepository;
import app.forum.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SzukajService {

    private DzialRepository dzialRepository;
    private KomentarzRepository komentarzRepository;
    private PostRepository postRepository;
    private UzytkownikRepository uzytkownikRepository;

    @Autowired
    public SzukajService(DzialRepository dzialRepository, KomentarzRepository komentarzRepository,
                         PostRepository postRepository, UzytkownikRepository uzytkownikRepository) {
        this.dzialRepository = dzialRepository;
        this.komentarzRepository = komentarzRepository;
        this.postRepository = postRepository;
        this.uzytkownikRepository = uzytkownikRepository;
    }

    public void znajdzPoFrazie(String fraza){

        List<Post> listaPostow=postRepository.znajdzPoFrazie(fraza);

        System.out.println(listaPostow);
    }
}
