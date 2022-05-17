package app.forum.repository;

import app.forum.model.Sesja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SesjaRepository extends JpaRepository<Sesja,Long> {
    @Query("select s from Sesja s where s.klucz=:klucz")
    Sesja pobierzPoKluczu(@Param("klucz") String klucz);
}
