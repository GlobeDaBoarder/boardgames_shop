package ua.rivnegray.boardgames_shop.exceptions.handler;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.rivnegray.boardgames_shop.exceptions.CustomErrorAttributes;
import ua.rivnegray.boardgames_shop.exceptions.badRequestExceptions.BadRequestException;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.ConflictException;
import ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions.InternalServerException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.ResourceIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(ResourceIdNotFoundException.class)
    public ResponseEntity<CustomErrorAttributes> handleResourceIdNotFoundException(ResourceIdNotFoundException ex, HttpServletRequest request) {
        LOGGER.error(ex.getMessage(), ex);

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(httpStatus)
                .body(CustomErrorAttributes.builder()
                        .httpStatus(httpStatus)
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorAttributes> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        LOGGER.error(ex.getMessage(), ex);

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(httpStatus)
                .body(CustomErrorAttributes.builder()
                        .httpStatus(httpStatus)
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }


    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<CustomErrorAttributes> handleConflictException(ConflictException ex, HttpServletRequest request) {
        LOGGER.error(ex.getMessage(), ex);

        HttpStatus httpStatus = HttpStatus.CONFLICT;
        return ResponseEntity
                .status(httpStatus)
                .body(CustomErrorAttributes.builder()
                        .httpStatus(httpStatus)
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<CustomErrorAttributes> handleInternalServerException(InternalServerException ex, HttpServletRequest request) {
        LOGGER.error(ex.getMessage(), ex);

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(httpStatus)
                .body(CustomErrorAttributes.builder()
                        .httpStatus(httpStatus)
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorAttributes> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        LOGGER.error(ex.getMessage(), ex);

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(CustomErrorAttributes.builder()
                        .httpStatus(httpStatus)
                        .message(String.valueOf(ex.getDetailMessageArguments()[1]))
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomErrorAttributes> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        LOGGER.error(ex.getMessage(), ex);

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(CustomErrorAttributes.builder()
                        .httpStatus(httpStatus)
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }
}
