package app.forum.service;

import app.forum.exception.BaseException;
import app.forum.model.Ban;
import app.forum.model.Uzytkownik;
import app.forum.repository.BanRepository;
import app.forum.utils.DodajBanaRequest;
import app.forum.utils.OdpowiedzBazowa;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired; ;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Log4j2
@Service
public class BanService {

    @Autowired
    private BanRepository banRepository;

    @Autowired
    private UzytkownikService uzytkownikService;

//    @Transactional
    public OdpowiedzBazowa dodajBana(DodajBanaRequest request){
        OdpowiedzBazowa odp = new OdpowiedzBazowa();
        try {
            validujRequest(request);
            Uzytkownik uzytkownikDoZbanowania = uzytkownikService.pobierzPoId(request.getUId());
            Ban ban = stworzBana(uzytkownikDoZbanowania,request);
            banRepository.save(ban);
            odp.setSukces(true);
            odp.setKomunikat("Uzytkownik "+uzytkownikDoZbanowania.getNazwa()+" zostal zbanowany");
            return odp;
        } catch (Exception e){
            e.printStackTrace();
            odp.setSukces(false);
            odp.setKomunikat(e.getMessage());
            throw new BaseException(e.getMessage(), HttpStatus.BAD_REQUEST,odp);
        }
    }

    private Ban stworzBana(Uzytkownik uzytkownikDoZbanowania, DodajBanaRequest request) {

        Uzytkownik dodajacyBana = uzytkownikService.zwrocZalogowanego();
        if(dodajacyBana.getNazwa().equals("anonymousUser")){
            throw new IllegalArgumentException("Nie wiadomo kto daje bana");
        }
        Ban ban = Ban.builder()
                .czyAktywny(true)
                .dataWygasniecia(request.getDataWygasniecia())
                .dataZalozenia(LocalDateTime.now())
                .zalozonePrzez(dodajacyBana)
                .uzytkownik(uzytkownikDoZbanowania)
                .powodBana(request.getPowod())
                .build();
        return ban;
    }

    private void validujRequest(DodajBanaRequest request) {


        if(request.getUId() == null){
            throw new IllegalArgumentException("Brak id uzytkownika");
        }
        Uzytkownik uzytkownikDoZbanowania = uzytkownikService.pobierzPoId(request.getUId());

        if(uzytkownikDoZbanowania == null){
            throw new RuntimeException("Brak takiego uzytkownika");
        }
        if(uzytkownikDoZbanowania.getRole().stream()
                .anyMatch(r -> r.equals("ROLE_ADMIN"))){
            throw new IllegalCallerException("Nie mozesz zbanowac admina");
        }
        if(request.getPowod() == null || request.getPowod().isEmpty()){
            throw new IllegalArgumentException("Brak powodu bana");
        }
    }
    public OdpowiedzBazowa odbanuj(Long id){
        OdpowiedzBazowa odp = new OdpowiedzBazowa();
        try {
            Ban ban = banRepository.getById(id);
            ban.setDataWygasniecia(LocalDateTime.now());
            ban.setCzyAktywny(false);
            banRepository.save(ban);
            odp.setSukces(true);
            odp.setKomunikat("Udało się odbanować użytkownika");
            return odp;
        } catch (Exception e){
            odp.setSukces(false);
            odp.setKomunikat(e.getMessage());
            throw new BaseException(e.getMessage(),HttpStatus.BAD_REQUEST,odp);
        }
    }

    public void sprawdzBany(Uzytkownik uzytkownik) {
        for(Ban ban : uzytkownik.getListaBanow()){
            if(ban.getCzyAktywny()){
                if(ban.getDataWygasniecia() != null && ban.getDataWygasniecia().isBefore(LocalDateTime.now())){
                    ban.setCzyAktywny(false);
                    banRepository.save(ban);
                }
            }
        }
    }
}
