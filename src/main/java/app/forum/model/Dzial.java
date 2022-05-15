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
public class Dzial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nazwaDzialu;
    private String opis;

    @OneToMany(mappedBy = "dzial",cascade = CascadeType.ALL)
    private List<Post> posty;


}
