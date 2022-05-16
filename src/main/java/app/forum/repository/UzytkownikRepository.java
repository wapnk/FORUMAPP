package app.forum.repository;

import app.forum.model.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UzytkownikRepository extends JpaRepository<Uzytkownik, Long> {


    @Query("select u from Uzytkownik u where u.nazwa=:username")
    Uzytkownik pobierzPoNazwie(@Param("username") String username);

}
