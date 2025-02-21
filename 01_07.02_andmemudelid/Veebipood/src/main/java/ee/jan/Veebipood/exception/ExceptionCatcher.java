package ee.jan.Veebipood.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice // läheb automaatikiaga üle kõikide controllerite ja handle-b veateateid

public class ExceptionCatcher {
    // TODO: Selgita responseentity

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException e){
        ErrorMessage error = new ErrorMessage();
        error.setMessage(e.getMessage());
        error.setTimestamp(new Date());
        error.setStatus(400);
        return ResponseEntity.badRequest().body(new ErrorMessage());
    }
}
