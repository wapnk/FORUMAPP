package app.forum.dto;

import app.forum.model.Dzial;
import app.forum.model.Odpowiedz;
import app.forum.model.Uzytkownik;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nazwa;
    private String tresc;

    @OneToMany(mappedBy = "post")
    private List<Odpowiedz> odpowiedzi;


    @ManyToOne
    private Uzytkownik zalozonePrzez;
    private LocalDateTime dataZalozenia;
    private String zamknietePrzez;
    private LocalDateTime dataZamkniecia;

    @ManyToOne
    private Dzial dzial;

}
