package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class BoardGameIdNotFoundException extends ResourceIdNotFoundException {
    public BoardGameIdNotFoundException(Long id) {
        super("Board game id not found");
    }
}
