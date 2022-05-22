package app.forum.model;

import lombok.*;

import javax.persistence.*;
//import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sesja  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(cascade = CascadeType.MERGE)
    private Uzytkownik uzytkownik;

    private String klucz;

    private LocalDateTime dataUtworzenia;

    private LocalDateTime dataZakonczenia;

    private Long dlugoscSesji;

    private String adresIP;

    @Override
    public String toString() {
        return "toString";
    }
}
