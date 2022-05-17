package app.forum.service;

import app.forum.model.Uzytkownik;
import app.forum.repository.UzytkownikRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UzytkownikService implements UserDetailsService {

    @Autowired
    private UzytkownikRepository uzytkownikRepository;

    public Uzytkownik zapisz(Uzytkownik uzytkownik) {
        return uzytkownikRepository.save(uzytkownik);
    }

    public Uzytkownik zwrocZalogowanego() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("=============== AUTHENTICATION");
        log.info(authentication.getPrincipal());
        log.info(authentication.getAuthorities());
        log.info(authentication.getCredentials());
        log.info(authentication.getDetails());
        String nazwa = authentication.getName();

        if (!authentication.isAuthenticated()) {
            log.info("uzytkownik " + nazwa + " is not authenticated");
        }
        Uzytkownik uzytkownik = loadUserByUsername(nazwa);

        if (uzytkownik == null) {
            log.info("Brak takiego uzytkownika w bazie !");
            return Uzytkownik.builder()
                    .haslo(null)
                    .nazwa(nazwa)
                    .email(null)
                    .id(null)
                    .odpowiedzi(null)
                    .posty(null)
                    .build();
        }


        return uzytkownik;
    }

    @Override
    public Uzytkownik loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Uzytkownik> listaUzytkowniow = uzytkownikRepository.pobierzPoNazwie(username);

        if (listaUzytkowniow == null || listaUzytkowniow.isEmpty()) {
            log.info("Brak uzytkownika " + username + " w bazie");
            return null;
        } else {
            listaUzytkowniow.forEach(u -> log.info("Znaleziony uzytkownik : " + u.getNazwa()));
            listaUzytkowniow = listaUzytkowniow.stream().filter(u -> u.getNazwa().equals(username)).collect(Collectors.toList());
            if (listaUzytkowniow == null || listaUzytkowniow.isEmpty()) {
                log.info("Brak uzytkownika " + username + " w bazie");
                return null;
            } else {
                return listaUzytkowniow.get(0);
            }
        }
    }

}
