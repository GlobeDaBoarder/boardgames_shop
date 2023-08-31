package ua.rivnegray.boardgames_shop.exceptions;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
public
class CustomErrorAttributes{
    private final ZonedDateTime timestamp;
    private final Integer code;
    private final String error;
    private final String message;
    private final String path;

    @Builder
    public CustomErrorAttributes(HttpStatus httpStatus, String message, String path) {
        this.timestamp = ZonedDateTime.now();
        this.code = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
    }
}
