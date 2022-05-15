package app.forum.restcontroller;

import app.forum.model.Dzial;
import app.forum.service.DzialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dzial")
public class DzialRestController {

    @Autowired
    private DzialService dzialService;

    @GetMapping("/pobierzDzialy")
    public List<Dzial> pobierzDzialy (){
        return dzialService.pobierzDzialy();
    }
}
