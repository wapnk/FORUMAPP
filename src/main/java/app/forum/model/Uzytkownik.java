package app.forum.model;

import app.forum.exception.BaseException;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "uzytkownik", fetch = FetchType.EAGER)
    private List<Ban> listaBanow;
    @OneToMany(mappedBy = "zalozonePrzez")
    private List<Ban> rozdaneBany;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> role;

    //    @OneToMany(mappedBy = "zablokowanyPrzez")
//    private List<Uzytkownik> zablokowaniUzytkownicy;
//
//    @ManyToOne(cascade = CascadeType.MERGE)
//    private Uzytkownik zablokowanyPrzez;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities;
        if (role != null && !role.isEmpty()) {
            authorities = role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            return authorities;
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));

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
        if (nazwa.equals("anonymousUser")) {
            return true;
        }
        return listaBanow.stream().noneMatch(ban -> ban.getCzyAktywny().equals(Boolean.TRUE));
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void dodajRole(String rola) {
        role.add(rola);
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
