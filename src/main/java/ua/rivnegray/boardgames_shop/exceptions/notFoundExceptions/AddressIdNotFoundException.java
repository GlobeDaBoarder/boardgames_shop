package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;


import ua.rivnegray.boardgames_shop.model.Address;

public class AddressIdNotFoundException extends ResourceIdNotFoundException {
    public AddressIdNotFoundException(Long id) {
        super(Address.class.getName(), id);
    }

}
