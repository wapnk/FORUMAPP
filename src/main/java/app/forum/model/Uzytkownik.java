package app.forum.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nazwa;
    private String email;
    private String haslo;

    @OneToMany(mappedBy = "zalozonePrzez")
    private List<Post> posty;

    @OneToMany(mappedBy = "uzytkownik")
    private List<Odpowiedz> odpowiedzi;

}
