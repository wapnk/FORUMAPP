package app.forum.service;

import app.forum.model.Uzytkownik;
import app.forum.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UzytkownikService implements UserDetailsService {

    @Autowired
    private UzytkownikRepository uzytkownikRepository;

    public Uzytkownik zapisz(Uzytkownik uzytkownik) {
        return uzytkownikRepository.save(uzytkownik);
    }

    @Override
    public Uzytkownik loadUserByUsername(String username) throws UsernameNotFoundException {
        return uzytkownikRepository.pobierzPoNazwie(username);
    }
}
