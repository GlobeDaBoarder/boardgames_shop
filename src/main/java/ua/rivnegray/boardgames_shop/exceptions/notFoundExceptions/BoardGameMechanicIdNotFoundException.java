package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class BoardGameMechanicIdNotFoundException extends ResourceIdNotFoundException{
    public BoardGameMechanicIdNotFoundException(Long id) {
        super("Board game mechanic with id " + id + " not found");
    }
}
