package app.forum.service;

import app.forum.model.Sesja;
import app.forum.model.Uzytkownik;
import app.forum.repository.SesjaRepository;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Log4j2
@Service
public class SesjaService {

    @Autowired
    private SesjaRepository sesjaRepository;


    public void zarejestrujSesje(Uzytkownik uzytkownik) {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        log.info("getCreationTime "+attr.getRequest().getSession().getCreationTime());
        log.info("getAttributeNames "+attr.getRequest().getSession().getAttributeNames());
        log.info("getId "+attr.getRequest().getSession().getId());
        log.info("getMaxInactiveInterval "+attr.getRequest().getSession().getMaxInactiveInterval());


        Sesja sesja = Sesja.builder()
                .adresIP(attr.getRequest().getRemoteAddr())
                .dataUtworzenia(LocalDateTime.now())
                .dataZakonczenia(null)
                .dlugoscSesji((long) session.getMaxInactiveInterval())
                .klucz(session.getId())
                .uzytkownik(uzytkownik)
                .build();

        sesjaRepository.save(sesja);

    }
    //TODO
    public void zamknijSesje(Sesja sesja){

        sesja.setDataZakonczenia(LocalDateTime.now());
        sesjaRepository.save(sesja);
    }
}
