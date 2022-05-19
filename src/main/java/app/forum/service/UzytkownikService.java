package app.forum.service;

import app.forum.model.Uzytkownik;
import app.forum.repository.UzytkownikRepository;
import app.forum.utils.OdpowiedzBazowa;
import app.forum.utils.RejestrujUzytkownikaRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    public OdpowiedzBazowa zarejestrujUzytkownika(RejestrujUzytkownikaRequest request) {

        OdpowiedzBazowa odpowiedz = new OdpowiedzBazowa();
        try {
            validujRequest(request);
            Uzytkownik uzytkownik = stworzUzytkownika(request);
            uzytkownikRepository.save(uzytkownik);
            odpowiedz.setSukces(true);
            odpowiedz.setKomunikat("Udalo sie zarejestrowac uzytkownika");
            return odpowiedz;

        } catch (Exception e) {
            e.printStackTrace();
            odpowiedz.setSukces(false);
            odpowiedz.setKomunikat(e.getMessage());
            return odpowiedz;
        }
    }

    private Uzytkownik stworzUzytkownika(RejestrujUzytkownikaRequest request) {
        Uzytkownik uzytkownik = Uzytkownik.builder()
                .nazwa(request.getNazwa())
                .haslo(request.getHaslo1())
                .email(request.getEmail())
                .build();
        return uzytkownik;
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
                    .komentarze(null)
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


    private void validujRequest(RejestrujUzytkownikaRequest u) {

        if (u.getNazwa() == null || u.getNazwa().isEmpty()) {
            throw new IllegalStateException("Nazwa pusta");
        }
        if (u.getHaslo1() == null || u.getHaslo1().isEmpty()) {
            throw new IllegalStateException("Puste haslo");
        }
        if (!u.getHaslo1().equals(u.getHaslo2())) {
            throw new IllegalStateException("Hasla nie sa takie same");
        }
        if (!czySilneHaslo(u.getHaslo1())) {
            throw new IllegalStateException("Haslo zbyt slabe");
        }
        if (loadUserByUsername(u.getNazwa()) != null) {
            throw new IllegalStateException("Taki uzytkownik juz istnieje");
        }
    }

    private boolean czySilneHaslo(String haslo) {

        return true;
    }

    public Uzytkownik zapisz(Uzytkownik uzytkownik) {
        return uzytkownikRepository.save(uzytkownik);
    }
}
