package ua.rivnegray.boardgames_shop.exceptions;

public class BoardGameIdNotFoundException extends RuntimeException {
    public BoardGameIdNotFoundException(Long id) {
        super("Board game with id " + id + " not found");
    }
}
