package ua.rivnegray.boardgames_shop.service;

import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameMechanicDto;

import java.util.List;

public interface BoardGameMechanicService {

    @Transactional(readOnly = true)
    List<BoardGameMechanicDto> getAllMechanics();

    @Transactional(readOnly = true)
    BoardGameMechanicDto getMechanicById(Long id);

    @Transactional
    BoardGameMechanicDto addMechanic(CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto);

    @Transactional
    BoardGameMechanicDto updateMechanic(Long id, CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto);

    @Transactional
    void deleteMechanic(Long id);
}
