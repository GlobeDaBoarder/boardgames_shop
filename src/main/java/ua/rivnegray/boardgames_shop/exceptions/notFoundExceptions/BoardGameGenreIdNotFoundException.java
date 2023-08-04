package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.BoardGameGenre;

public class BoardGameGenreIdNotFoundException extends ResourceIdNotFoundException {
    public BoardGameGenreIdNotFoundException(Long id) {
        super(BoardGameGenre.class.getName(), id);
    }
}
