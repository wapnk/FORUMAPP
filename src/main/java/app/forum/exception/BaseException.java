package app.forum.exception;

import app.forum.utils.OdpowiedzBazowa;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final OdpowiedzBazowa odpowiedzBazowa;

    public BaseException(String message, HttpStatus httpStatus, OdpowiedzBazowa odpowiedzBazowa) {
        super(message);
        this.httpStatus = httpStatus;
        this.odpowiedzBazowa = odpowiedzBazowa;
    }

    public BaseException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.odpowiedzBazowa = OdpowiedzBazowa.builder()
                .sukces(false)
                .komunikat(message)
                .build();
    }
}
