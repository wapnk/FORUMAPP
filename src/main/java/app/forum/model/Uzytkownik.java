package app.forum.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Uzytkownik implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nazwa;
    private String email;
    private String haslo;

    private LocalDateTime dataUtworzenia;
    @OneToMany(mappedBy = "zalozonePrzez")
    private List<Post> posty;
    @OneToMany(mappedBy = "uzytkownik")
    private List<Komentarz> komentarze;
    @OneToMany(mappedBy = "uzytkownik")
    private List<Sesja> sesje;

    @OneToMany(mappedBy = "uzytkownik")
    private List<Ban> listaBanow;

    @OneToMany(mappedBy = "zalozonePrzez")
    private List<Ban> rozdaneBany;

//    @OneToMany(mappedBy = "zablokowanyPrzez")
//    private List<Uzytkownik> zablokowaniUzytkownicy;
//
//    @ManyToOne
//    private Uzytkownik zablokowanyPrzez;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return haslo;
    }

    @Override
    public String getUsername() {
        return nazwa;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        boolean czyZbanowany = false;
        czyZbanowany=listaBanow.stream().noneMatch(ban -> ban.getCzyAktywny().equals(Boolean.TRUE));
        return czyZbanowany;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "Uzytkownik{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", email='" + email + '\'' +
                ", haslo='" + haslo + '\'' +
                '}';
    }

}
