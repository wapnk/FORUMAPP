package app.forum.exception;

import app.forum.utils.OdpowiedzBazowa;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ForumExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error/blad";

    @ExceptionHandler({Exception.class, BaseException.class})
    protected ModelAndView handleConflict(Exception ex, WebRequest request) {
//    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {

        String odpowiedz;
        HttpStatus status;
        ModelAndView widok = new ModelAndView();
        OdpowiedzBazowa odpowiedzBazowa; // wykorzystywane przy odpowiedziach dla rest api

        if (ex instanceof BaseException) {
            BaseException e = (BaseException) ex;
            e.printStackTrace();
            odpowiedz = e.getMessage();
            status = e.getHttpStatus();
            odpowiedzBazowa = e.getOdpowiedzBazowa();
        } else {
            ex.printStackTrace();
            odpowiedz = "Wystąpił wewnętrzny błąd serwera";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            odpowiedzBazowa = OdpowiedzBazowa.builder()
                    .sukces(false)
                    .komunikat(odpowiedz)
                    .build();
        }
        widok.setViewName(DEFAULT_ERROR_VIEW);
        widok.setStatus(status);
        widok.addObject("status", status.value());
        widok.addObject("msg", odpowiedz);
        return widok;
//        return handleExceptionInternal(ex, odpowiedzBazowa, new HttpHeaders(), status, request);
    }
}