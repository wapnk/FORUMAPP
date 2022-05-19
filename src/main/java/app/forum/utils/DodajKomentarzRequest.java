package app.forum.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DodajKomentarzRequest {
    public String tresc;
    public Long postId;
}
