package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class BoardGameGenreIdNotFoundException extends ResourceIdNotFoundException {
    public BoardGameGenreIdNotFoundException(Long id) {
        super("Board game genre id not found");
    }
}
