package app.forum.service;

import app.forum.model.Dzial;
import app.forum.model.Post;
import app.forum.repository.DzialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DzialService  {


    @Autowired
    private DzialRepository dzialRepository;

    public Dzial pobierzDzialPoId(Long id){
        return dzialRepository.getById(id);
    }
    public List<Dzial> pobierzDzialy() {
        List<Dzial> dzialy = dzialRepository.getAll();
        return dzialy;
    }


    public List<Dzial> zapiszWszystkie(List<Dzial> posty) {
        return dzialRepository.saveAll(posty);
    }
}
