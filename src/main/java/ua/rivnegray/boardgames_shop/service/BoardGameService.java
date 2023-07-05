package ua.rivnegray.boardgames_shop.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;

import java.util.List;

public interface BoardGameService {
    @Transactional(readOnly = true)
    List<BoardGameDto> getAllBoardGames();

    @Transactional
    BoardGameDto addBoardGame(CreateAndUpdateBoardGameDto createBoardGameDto);

    @Transactional(readOnly = true)
    BoardGameDto getBoardGameById(Long id);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    BoardGameDto updateBoardGame(Long id, CreateAndUpdateBoardGameDto updateBoardGameDto);

    @Transactional
    void deleteBoardGame(Long id);
}
