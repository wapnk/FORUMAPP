package app.forum.utils;


import app.forum.model.Dzial;
import app.forum.model.Post;
import app.forum.service.DzialService;
import app.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Zasilator {

    @Autowired
    private PostService postService;
    @Autowired
    private DzialService dzialService;

    @PostConstruct
    public void met(){
        posty();
    }

    public void dzialy(){

        Dzial dzial1=Dzial.builder().nazwaDzialu("Dzial pierwszy").opis("Krotki opis ").build();
        Dzial dzial2=Dzial.builder().nazwaDzialu("Dzial drugi").opis("Krotki opis ").build();

        List<Post> posty1=posty();
        List<Post> posty2=posty();

        dzial1.setPosty(posty1);
        dzial2.setPosty(posty2);
//        posty1.forEach(post -> post.setDzial(dzial1));
//        posty2.forEach(post -> post.setDzial(dzial2));

//        dzialService.zapiszWszystkie(List.of(dzial1,dzial2));
        postService.zapiszWszystkie(posty1);
        List<Dzial> dzialy=List.of(dzial1,dzial2);
    }
    public List<Post> posty(){
        Post post1=Post.builder()
                .dataZalozenia(LocalDateTime.now())
                .dataZamkniecia(LocalDateTime.now())
                .nazwa("Nazwa postu")
                .tresc("Tresc abcheadjd iasd hszkjka dkjsakj ")
                .zalozonePrzez(null)
                .zamknietePrzez("null")
                .build();
        Post post2=Post.builder()
                .dataZalozenia(LocalDateTime.now())
                .dataZamkniecia(LocalDateTime.now())
                .nazwa("Nazwa postu")
                .tresc("Tresc abcheadjd iasd hszkjka dkjsakj ")
                .zalozonePrzez(null)
                .zamknietePrzez("null")
                .build();
        Post post3=Post.builder()
                .dataZalozenia(LocalDateTime.now())
                .dataZamkniecia(LocalDateTime.now())
                .nazwa("Nazwa postu")
                .tresc("Tresc abcheadjd iasd hszkjka dkjsakj ")
                .zalozonePrzez(null)
                .zamknietePrzez(null)
                .build();
        List<Post> posty = List.of(post1,post2,post3);
        posty=postService.zapiszWszystkie(posty);
        System.out.println(posty.toString());
//asd

//        postService.zapisz(posty);
        return posty;
    }
}
