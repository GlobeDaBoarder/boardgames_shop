package ua.rivnegray.boardgames_shop.exceptions.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.ConflictException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.ResourceIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.ResourceNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.UsernameAlreadyTakenException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceIdNotFoundException.class)
    // todo check
    // todo make common parrent
    public ResponseEntity<String> handleResourceIdNotFoundException(ResourceIdNotFoundException ex) {
        // todo figure out logging (log4j??)
        // todo logging of exceptions, db requests  into file
        // todo log into db??
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }


    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflictException(ConflictException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

}
