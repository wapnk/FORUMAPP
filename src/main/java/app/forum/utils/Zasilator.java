package app.forum.utils;


import app.forum.model.Dzial;
import app.forum.model.Post;
import app.forum.model.Uzytkownik;
import app.forum.service.BanService;
import app.forum.service.DzialService;
import app.forum.service.PostService;
import app.forum.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class Zasilator {

    @Autowired
    private PostService postService;
    @Autowired
    private DzialService dzialService;
    @Autowired
    private UzytkownikService uzytkownikService;

    @Autowired
    private BanService banService;

    @PostConstruct
//    @Transactional
    public void met() {

        List<Dzial> dzialy = dzialy();

        dzialService.zapiszWszystkie(dzialy);

        Uzytkownik u1 = uzytkownikService.zapisz(Uzytkownik.builder()
                .id(1L)
                .haslo("123")
                .nazwa("user1")
                .role(Set.of("ROLE_USER"))
                .build());
        Uzytkownik u2 = uzytkownikService.zapisz(Uzytkownik.builder()
                .id(2L)
                .haslo("1234")
                .nazwa("user2")
                .role(Set.of("ROLE_USER"))
                .build());
        Uzytkownik u3 = uzytkownikService.zapisz(Uzytkownik.builder()
                .id(3L)
                .haslo("123")
                .nazwa("wapnk")
                .role(Set.of("ROLE_ADMIN"))
                .build());

        List<Post> posty1 = posty(null);
        List<Post> posty2 = posty(null);


        posty1.forEach(post -> post.setDzial(dzialy.get(0)));
        posty2.forEach(post -> post.setDzial(dzialy.get(1)));
        posty1.forEach(post -> post.setZalozonePrzez(u1));
        posty2.forEach(post -> post.setZalozonePrzez(u2));


        postService.zapiszWszystkie(posty1);
        postService.zapiszWszystkie(posty2);
    }


    //    @Transactional
    public List<Dzial> dzialy() {


        Dzial dzial1 = Dzial.builder().nazwaDzialu("Dzial pierwszy").opis("Krotki opis ").build();
        Dzial dzial2 = Dzial.builder().nazwaDzialu("Dzial drugi").opis("Krotki opis ").build();

        return dzialService.zapiszWszystkie(List.of(dzial1, dzial2));
    }


    public List<Post> posty(Uzytkownik u) {


        Post post1 = Post.builder()
                .dataZalozenia(null)
                .dataZamkniecia(null)
                .nazwa("Nazwa postu")
                .tresc("Tresc abcheadjd iasd hszkjka dkjsakj ")
                .zalozonePrzez(u)
                .zamknietePrzez("null")
                .build();
        Post post2 = Post.builder()
                .dataZalozenia(null)
                .dataZamkniecia(null)
                .nazwa("Nazwa postu")
                .tresc("Tresc abcheadjd iasd hszkjka dkjsakj ")
                .zalozonePrzez(u)
                .zamknietePrzez("null")
                .build();
        Post post3 = Post.builder()
                .dataZalozenia(null)
                .dataZamkniecia(null)
                .nazwa("Nazwa postu")
                .tresc("Tresc abcheadjd iasd hszkjka dkjsakj ")
                .zalozonePrzez(u)
                .zamknietePrzez(null)
                .build();
        return List.of(post1, post2, post3);
    }
}
