package app.forum.service;

import app.forum.model.Dzial;
import app.forum.repository.DzialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DzialService  {


    @Autowired
    private DzialRepository repository;
    public List<Dzial> pobierzDzialy() {
        return repository.getAll();
    }


}
