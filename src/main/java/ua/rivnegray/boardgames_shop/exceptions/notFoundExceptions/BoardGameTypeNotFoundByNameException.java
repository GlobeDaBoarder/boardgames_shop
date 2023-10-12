package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.BoardGameType;

public class BoardGameTypeNotFoundByNameException extends ResourceNotFoundException{
    public BoardGameTypeNotFoundByNameException(String boardGameTypeName) {
        super(BoardGameType.class.getName(), boardGameTypeName);
    }
}
