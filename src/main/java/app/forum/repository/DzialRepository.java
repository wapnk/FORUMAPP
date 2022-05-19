package app.forum.repository;

import app.forum.model.Dzial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DzialRepository extends JpaRepository<Dzial,Long> {
//    @Query(value = "select * from dzial",nativeQuery = true)
    @Query(value = "select d from Dzial d",nativeQuery = false)
    List<Dzial> getAll();
}
