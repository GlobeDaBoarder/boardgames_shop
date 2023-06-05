package ua.rivnegray.boardgames_shop.exceptions.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.rivnegray.boardgames_shop.exceptions.UserIdNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserIdNotFoundException ex) {
        // todo figure out logging (log4j??)
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // todo add more exception handlers


}
