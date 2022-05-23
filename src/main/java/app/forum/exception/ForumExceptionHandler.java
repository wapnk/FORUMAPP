package app.forum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ForumExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error/blad";
    @ExceptionHandler(BaseException.class)
    protected ModelAndView handleConflict(BaseException ex, WebRequest request) {
//    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {

        ex.printStackTrace();
        String odpowiedz = ex.getMessage();
        HttpStatus status = ex.getHttpStatus();
        ModelAndView widok = new ModelAndView();
        widok.setViewName(DEFAULT_ERROR_VIEW);
        widok.setStatus(status);
        widok.addObject("status",status.value());
        widok.addObject("msg",odpowiedz);
        return widok;
//        return handleExceptionInternal(ex, odpowiedz, new HttpHeaders(), status, request);
    }
}