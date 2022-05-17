package app.forum.repository;

import app.forum.model.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UzytkownikRepository extends JpaRepository<Uzytkownik, Long> {


    @Query("select u from Uzytkownik u where u.nazwa like :username")
    List<Uzytkownik> pobierzPoNazwie(@Param("username") String username);

}
