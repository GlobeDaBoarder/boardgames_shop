package ua.rivnegray.boardgames_shop.exceptions;

public class AddressIdNotFoundException extends RuntimeException {
    public AddressIdNotFoundException(Long addressId) {
        super("Address with id " + addressId + " not found");
    }
}
