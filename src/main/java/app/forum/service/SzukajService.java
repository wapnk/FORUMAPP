package app.forum.service;

import app.forum.model.Post;
import app.forum.repository.DzialRepository;
import app.forum.repository.OdpowiedzRepository;
import app.forum.repository.PostRepository;
import app.forum.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SzukajService {

    private DzialRepository dzialRepository;
    private OdpowiedzRepository odpowiedzRepository;
    private PostRepository postRepository;
    private UzytkownikRepository uzytkownikRepository;

    @Autowired
    public SzukajService(DzialRepository dzialRepository, OdpowiedzRepository odpowiedzRepository,
                         PostRepository postRepository, UzytkownikRepository uzytkownikRepository) {
        this.dzialRepository = dzialRepository;
        this.odpowiedzRepository = odpowiedzRepository;
        this.postRepository = postRepository;
        this.uzytkownikRepository = uzytkownikRepository;
    }

    public void znajdzPoFrazie(String fraza){

        List<Post> listaPostow=postRepository.znajdzPoFrazie(fraza);

        System.out.println(listaPostow);
    }
}
