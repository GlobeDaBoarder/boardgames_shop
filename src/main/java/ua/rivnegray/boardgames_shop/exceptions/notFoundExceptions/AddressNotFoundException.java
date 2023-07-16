package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class AddressNotFoundException extends ResourceNotFoundException {
    public AddressNotFoundException(String noAddressFoundForUser) {
        super(noAddressFoundForUser);
    }
}
