package app.forum.dto;

import app.forum.model.Komentarz;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;
@Builder
public class PostDTO {

    public Long id;
    public String nazwa;
    public String tresc;
    public String zalozonePrzez;
    public List<Komentarz> odpowiedzi;
    public LocalDateTime dataZalozenia;



}
