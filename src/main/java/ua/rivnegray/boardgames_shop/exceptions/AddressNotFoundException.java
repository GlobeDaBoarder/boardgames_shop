package ua.rivnegray.boardgames_shop.exceptions;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String noAddressFoundForUser) {
        super(noAddressFoundForUser);
    }
}
