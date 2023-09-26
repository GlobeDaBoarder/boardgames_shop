package ua.rivnegray.boardgames_shop.DTO.response;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.BoardGameMechanic}
 */
@Builder
public record BoardGameMechanicDto(Long id, String mechanicName, String mechanicDescription) implements Serializable {
}
