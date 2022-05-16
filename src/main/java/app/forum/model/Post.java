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
//@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nazwa;
    private String tresc;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private List<Odpowiedz> odpowiedzi;


    @ManyToOne(fetch = FetchType.EAGER)
    private Uzytkownik zalozonePrzez;
    private LocalDateTime dataZalozenia;
    private String zamknietePrzez;
    private LocalDateTime dataZamkniecia;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Dzial dzial;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", tresc='" + tresc + '\'' +
                ", dataZalozenia=" + dataZalozenia +
                ", zamknietePrzez='" + zamknietePrzez + '\'' +
                ", dataZamkniecia=" + dataZamkniecia +
                '}';
    }
}
