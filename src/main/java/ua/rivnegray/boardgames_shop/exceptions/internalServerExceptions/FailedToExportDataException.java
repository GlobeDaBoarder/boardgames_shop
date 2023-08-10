package ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions;

public class FailedToExportDataException extends InternalServerException{
    public FailedToExportDataException(Throwable cause) {
        super("Failed to export data", cause);
    }
}
