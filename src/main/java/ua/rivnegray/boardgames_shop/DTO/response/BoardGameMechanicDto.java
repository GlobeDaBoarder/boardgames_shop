package ua.rivnegray.boardgames_shop.DTO.response;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.BoardGameMechanic}
 */
public record BoardGameMechanicDto(Long id, String mechanicName, String mechanicDescription) implements Serializable {
}
