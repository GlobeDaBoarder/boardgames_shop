package ua.rivnegray.boardgames_shop.exceptions.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.rivnegray.boardgames_shop.exceptions.AddressIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.AddressNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.BoardGameGenreIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.BoardGameMechanicIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.OrderIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.RoleIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.RoleNameNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.ShoppingCartIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.UserIdNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserIdNotFoundException.class, AddressIdNotFoundException.class, AddressNotFoundException.class,
            BoardGameGenreIdNotFoundException.class, BoardGameIdNotFoundException.class,
            BoardGameMechanicIdNotFoundException.class, OrderIdNotFoundException.class, RoleIdNotFoundException.class,
            RoleNameNotFoundException.class, ShoppingCartIdNotFoundException.class, UserIdNotFoundException.class
    })
    // todo check
    // todo make common parrent
    public ResponseEntity<String> handleUserNotFoundException(RuntimeException ex) {
        // todo figure out logging (log4j??)
        // todo logging of exceptions, db requests  into file
        // todo log into db??
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
