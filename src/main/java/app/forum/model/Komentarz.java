package app.forum.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Komentarz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tresc;
    private LocalDateTime dataDodania;

    @ManyToOne
    private Uzytkownik uzytkownik;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    @Override
    public String toString() {
        return "Komentarz{" +
                "id=" + id +
                ", tresc='" + tresc + '\'' +
                ", dataDodania=" + dataDodania +
                '}';
    }
}
