package app.forum.restcontroller;

import app.forum.service.SzukajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SzukajRestController {

    @Autowired
    private SzukajService szukajService;

    @GetMapping("/szukaj/{fraza}")
    public void szukaj(@PathVariable String fraza) {
        szukajService.znajdzPoFrazie(fraza);
    }

}
