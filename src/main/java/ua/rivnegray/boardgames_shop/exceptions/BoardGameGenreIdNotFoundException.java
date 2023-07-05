package ua.rivnegray.boardgames_shop.exceptions;

public class BoardGameGenreIdNotFoundException extends RuntimeException {
    public BoardGameGenreIdNotFoundException(Long id) {
        super("Board game genre with id " + id + " not found");
    }
}
