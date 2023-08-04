package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;

public class BoardGameMechanicIdNotFoundException extends ResourceIdNotFoundException{
    public BoardGameMechanicIdNotFoundException(Long id) {
        super(BoardGameMechanic.class.getName(), id);
    }
}
