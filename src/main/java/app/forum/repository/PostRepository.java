package app.forum.repository;

import app.forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select * from post", nativeQuery = true)
    List<Post> getAll();

    @Query(value = "select p from Post p where p.nazwa like '%:fraza%'", nativeQuery = false)
    List<Post> znajdzPoFrazie(@Param("fraza") String fraza);
}
