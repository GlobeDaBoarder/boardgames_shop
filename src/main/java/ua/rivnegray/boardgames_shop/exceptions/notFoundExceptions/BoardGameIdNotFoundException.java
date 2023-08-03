package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.BoardGame;

public class BoardGameIdNotFoundException extends ResourceIdNotFoundException {
    public BoardGameIdNotFoundException(Long id) {
        super(BoardGame.class.getName(), id);
    }
}
