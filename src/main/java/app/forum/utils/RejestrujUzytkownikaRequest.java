package app.forum.utils;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RejestrujUzytkownikaRequest {

    private String nazwa;
    private String email;
    private String haslo1;
    private String haslo2;


}
