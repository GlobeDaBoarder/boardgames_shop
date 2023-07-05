package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;

@Mapper(componentModel = "spring")
public interface BoardGameMechanicMapper {
    BoardGameMechanicDto boardGameMechanicToBoardGameMechanicDto(BoardGameMechanic boardGameMechanic);

    BoardGameMechanic boardGameMechanicDtoToBoardGameMechanic(BoardGameMechanicDto boardGameMechanicDto);

    BoardGameMechanic createBoardGameMechanicDtoToBoardGameMechanic(CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto);

    BoardGameMechanic updateBoardGameMechanicFromDto(CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto,
                                                     @MappingTarget BoardGameMechanic boardGameMechanic);
}
