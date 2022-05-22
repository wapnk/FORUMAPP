package app.forum.exception;

public class BanException extends RuntimeException{

    public BanException(String message) {
        super(message);
    }
}
