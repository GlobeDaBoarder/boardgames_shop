package ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions;

public class ExcelDataExportException extends InternalServerException{
    public ExcelDataExportException(Throwable cause) {
        super("Failed to export data", cause);
    }
}
