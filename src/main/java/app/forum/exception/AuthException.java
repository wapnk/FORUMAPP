package app.forum.exception;

import org.springframework.security.authentication.AccountStatusException;

public class AuthException extends AccountStatusException {
    public AuthException(String msg) {
        super(msg);
    }

    public AuthException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
