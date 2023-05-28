package ua.rivnegray.boardgames_shop.DTO.response;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.Address}
 */
public record AddressDto(Long id, String street, String houseNumber, String postalCode, String city,
                         String country) implements Serializable {
}
