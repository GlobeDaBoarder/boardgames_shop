package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;


// todo change to ResponseStatusException
public class AddressIdNotFoundException extends RuntimeException {
    public AddressIdNotFoundException(Long addressId) {
        super("Address id not found");
    }

    public AddressIdNotFoundException(){
        super("Address not found");
    }
}
