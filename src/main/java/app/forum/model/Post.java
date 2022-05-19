package app.forum.model;

import lombok.*;
import org.hibernate.annotations.Fetch;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;
    private String tresc;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Komentarz> komentarze;


    @ManyToOne
    private Uzytkownik zalozonePrzez;
    private LocalDateTime dataZalozenia;
    private String zamknietePrzez;
    private LocalDateTime dataZamkniecia;

    @ManyToOne(cascade = CascadeType.ALL)
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
