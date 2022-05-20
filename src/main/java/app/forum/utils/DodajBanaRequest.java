package app.forum.utils;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DodajBanaRequest {
    public Long id;
    public String powod;
    public LocalDateTime dataWygasniecia;
}
