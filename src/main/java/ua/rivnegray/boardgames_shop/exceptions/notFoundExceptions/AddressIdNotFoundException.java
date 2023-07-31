package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;


// todo change to ResponseStatusException
public class AddressIdNotFoundException extends RuntimeException {
    // todo add logging of IDs
    public AddressIdNotFoundException(Long addressId) {
        super("Address id not found");
    }

}
