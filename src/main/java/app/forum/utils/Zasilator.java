package app.forum.utils;


import app.forum.model.Dzial;
import app.forum.model.Post;
import app.forum.service.DzialService;
import app.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Zasilator {

    @Autowired
    private PostService postService;
    @Autowired
    private DzialService dzialService;

    @PostConstruct
    public void met() {
//        posty();
        dzialy();
    }


    @Transactional
    public void dzialy() {

        Dzial dzial1 = Dzial.builder().nazwaDzialu("Dzial pierwszy").opis("Krotki opis ").build();
        Dzial dzial2 = Dzial.builder().nazwaDzialu("Dzial drugi").opis("Krotki opis ").build();

        List<Post> posty1 = posty();
        List<Post> posty2 = posty();

        dzial1.setPosty(posty1);
        dzial2.setPosty(posty2);
        posty1.forEach(post -> post.setDzial(dzial1));
        posty2.forEach(post -> post.setDzial(dzial2));

//        postService.zapiszWszystkie(posty1);
        dzialService.zapiszWszystkie(List.of(dzial1, dzial2));


        List<Dzial> dzials=dzialService.pobierzDzialy();
        List<Post> posts=postService.pobierzPosty();
        System.out.println(dzials.toString());
        System.out.println(posts.toString());
    }


    public List<Post> posty() {
        Post post1 = Post.builder()
                .dataZalozenia(null)
                .dataZamkniecia(null)
                .nazwa("Nazwa postu")
                .tresc("Tresc abcheadjd iasd hszkjka dkjsakj ")
                .zalozonePrzez(null)
                .zamknietePrzez("null")
                .build();
        Post post2 = Post.builder()
                .dataZalozenia(null)
                .dataZamkniecia(null)
                .nazwa("Nazwa postu")
                .tresc("Tresc abcheadjd iasd hszkjka dkjsakj ")
                .zalozonePrzez(null)
                .zamknietePrzez("null")
                .build();
        Post post3 = Post.builder()
                .dataZalozenia(null)
                .dataZamkniecia(null)
                .nazwa("Nazwa postu")
                .tresc("Tresc abcheadjd iasd hszkjka dkjsakj ")
                .zalozonePrzez(null)
                .zamknietePrzez(null)
                .build();
        List<Post> posty = List.of(post1, post2, post3);
//        posty=postService.zapiszWszystkie(posty);
        System.out.println(posty.toString());

//        postService.zapisz(posty);
        return posty;
    }
}
