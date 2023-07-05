package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;

@Mapper(componentModel = "spring")
public interface BoardGameMechanicMapper {
    BoardGameMechanicDto boardGameMechanicToBoardGameMechanicDto(BoardGameMechanic boardGameMechanic);

    BoardGameMechanic boardGameMechanicDtoToBoardGameMechanic(BoardGameMechanicDto boardGameMechanicDto);
}
