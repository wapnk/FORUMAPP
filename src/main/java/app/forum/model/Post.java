package app.forum.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nazwa;
    private String tresc;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)

    private List<Odpowiedz> odpowiedzi;


    @ManyToOne
    private Uzytkownik zalozonePrzez;
    private LocalDateTime dataZalozenia;
    private String zamknietePrzez;
    private LocalDateTime dataZamkniecia;

    @ManyToOne
    private Dzial dzial;

}
