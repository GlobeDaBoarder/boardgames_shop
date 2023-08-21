package ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions;

public class UnsupportedFilenameException extends InternalServerException{

    public UnsupportedFilenameException(String filename) {
        super("Unsupported filename: " + filename);
    }
}
