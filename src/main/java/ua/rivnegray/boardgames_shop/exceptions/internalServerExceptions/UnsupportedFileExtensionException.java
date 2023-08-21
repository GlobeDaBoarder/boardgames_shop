package ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions;

public class UnsupportedFileExtensionException extends InternalServerException{

    public UnsupportedFileExtensionException(String extension) {
        super("Unsupported file extension: " + extension);
    }
}
