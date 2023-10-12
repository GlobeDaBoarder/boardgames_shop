package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class BoardGameLanguageNotFoundByNameException extends ResourceNotFoundException{
    public BoardGameLanguageNotFoundByNameException(String boardGameLanguageName) {
        super(BoardGameLanguageNotFoundByNameException.class.getName(), boardGameLanguageName);
    }
}
