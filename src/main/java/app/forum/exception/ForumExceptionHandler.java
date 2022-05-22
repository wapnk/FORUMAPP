package app.forum.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ForumExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {Throwable.class, Exception.class, RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        ex.printStackTrace();
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.LOCKED, request);
    }
}
