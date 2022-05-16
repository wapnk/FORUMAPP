package app.forum.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
//@ToString
public class Dzial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nazwaDzialu;
    private String opis;

    @OneToMany(mappedBy = "dzial",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Post> posty;

    @Override
    public String toString() {
        return "Dzial{" +
                "id=" + id +
                ", nazwaDzialu='" + nazwaDzialu + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}
