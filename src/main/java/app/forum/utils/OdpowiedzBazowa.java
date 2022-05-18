package app.forum.utils;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OdpowiedzBazowa {

    public boolean sukces;
    public String komunikat;

    public OdpowiedzBazowa(){
        this.sukces=false;
        this.komunikat="";

    }
}
