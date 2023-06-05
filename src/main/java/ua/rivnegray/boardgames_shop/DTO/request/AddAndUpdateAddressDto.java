package ua.rivnegray.boardgames_shop.DTO.request;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.Address}
 */
public record AddAndUpdateAddressDto(String street, String houseNumber, String postalCode, String city,
                                     String country) implements Serializable {
}
