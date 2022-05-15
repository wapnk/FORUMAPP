package app.forum.repository;

import app.forum.model.Odpowiedz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdpowiedzRepository extends JpaRepository<Odpowiedz,Long> {
}
