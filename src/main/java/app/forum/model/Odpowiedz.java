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
@Entity
@Builder
public class Odpowiedz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tresc;
    private LocalDateTime dataDodania;

    @ManyToOne
    private Uzytkownik uzytkownik;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Override
    public String toString() {
        return "Odpowiedz{" +
                "id=" + id +
                ", tresc='" + tresc + '\'' +
                ", dataDodania=" + dataDodania +
                '}';
    }
}
