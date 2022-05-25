package app.forum.utils;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DodajBanaRequest {
    public Long uId;
    public String powod;
    public LocalDateTime dataWygasniecia;
}
