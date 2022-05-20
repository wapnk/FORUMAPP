package app.forum.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ban {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataZalozenia;

    private LocalDateTime dataWygasniecia;

    private Boolean czyAktywny=Boolean.FALSE;

    @ManyToOne(cascade = CascadeType.ALL)
    private Uzytkownik uzytkownik;

    @ManyToOne(cascade = CascadeType.ALL)
    private Uzytkownik zalozonePrzez;

    private String powodBana;

}
