package ua.rivnegray.boardgames_shop.exceptions.badRequestExceptions;

public class InsufficientStockException extends BadRequestException {
    public InsufficientStockException(Long productId, Integer requestedAmount, Integer availableAmount) {
        super("Insufficient stock. The requested amount is higher then the available amount for product with id:" + productId
                + ". Requested amount: " +  requestedAmount
                + ". Available amount: " + availableAmount);
    }

    public InsufficientStockException(Integer requestedAmount, Integer availableAmount) {
        super("Insufficient stock. The requested amount is higher then the available amount. Requested amount: " +  requestedAmount
                + ". Available amount: " + availableAmount);
    }
}
