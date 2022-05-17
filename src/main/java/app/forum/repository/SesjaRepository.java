package app.forum.repository;

import app.forum.model.Sesja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesjaRepository extends JpaRepository<Sesja,Long> {
}
