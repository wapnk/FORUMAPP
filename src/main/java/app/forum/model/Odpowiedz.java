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

    @ManyToOne
    private Post post;

}
