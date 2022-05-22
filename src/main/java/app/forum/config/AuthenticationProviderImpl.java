package app.forum.config;

import app.forum.exception.AuthException;
import app.forum.model.Uzytkownik;
import app.forum.service.SesjaService;
import app.forum.service.UzytkownikService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SesjaService sesjaService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String nazwa = authentication.getName();
        String haslo = authentication.getCredentials().toString();
        Uzytkownik uzytkownik = uzytkownikService.loadUserByUsername(nazwa);

        if (uzytkownik == null) {
            throw new AuthException("Uzytkownik null");
        }
//        if(uzytkownik.getPassword().equals(passwordEncoder.encode(haslo))){
        if (uzytkownik.getPassword().equals(haslo)) {
            sprawdzPola(uzytkownik);
            sesjaService.zarejestrujSesje(uzytkownik);
            return new UsernamePasswordAuthenticationToken(uzytkownik.getUsername(), uzytkownik.getPassword(), uzytkownik.getAuthorities());
        } else {
            throw new AuthException("Nieprawidłowe dane");
        }
    }

    private void sprawdzPola(Uzytkownik uzytkownik) {
        if (!uzytkownik.isAccountNonLocked()) {
            throw new LockedException("Użytkownik " + uzytkownik.getNazwa() + " jest zablokowany");
        }
        if (!uzytkownik.isEnabled()) {
            throw new DisabledException("Konto jest wyłączone");
        }
        if (!uzytkownik.isAccountNonExpired()) {
            throw new AccountExpiredException("Konto wygasło");
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
