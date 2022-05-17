package app.forum.config;

import app.forum.model.Uzytkownik;
import app.forum.service.SesjaService;
import app.forum.service.UzytkownikService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j2
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    private UzytkownikService uzytkownikService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SesjaService sesjaService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{

        String nazwa = authentication.getName();
        String haslo = authentication.getCredentials().toString();
        Uzytkownik uzytkownik = uzytkownikService.loadUserByUsername(nazwa);
        if (uzytkownik == null) {
            return null;
        }

//        if(uzytkownik.getPassword().equals(passwordEncoder.encode(haslo))){
        if (uzytkownik.getPassword().equals(haslo)) {
            sesjaService.zarejestrujSesje(uzytkownik);
            return new UsernamePasswordAuthenticationToken(uzytkownik.getUsername(), uzytkownik.getPassword(), uzytkownik.getAuthorities());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
