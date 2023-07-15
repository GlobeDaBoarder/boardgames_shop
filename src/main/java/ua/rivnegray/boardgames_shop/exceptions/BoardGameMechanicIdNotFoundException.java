package ua.rivnegray.boardgames_shop.exceptions;

public class BoardGameMechanicIdNotFoundException extends RuntimeException{
    public BoardGameMechanicIdNotFoundException(Long id) {
        super("Board game mechanic with id " + id + " not found");
    }
}
