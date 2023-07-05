package ua.rivnegray.boardgames_shop.DTO.request.create;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.BoardGameMechanic}
 */
public record CreateAndUpdateBoardGameMechanicDto(String mechanicName,
                                                  String mechanicDescription) implements Serializable {
}
