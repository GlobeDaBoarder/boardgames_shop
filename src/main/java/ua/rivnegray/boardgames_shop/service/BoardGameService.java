package ua.rivnegray.boardgames_shop.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.FilterBoardGamesRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameSummaryDto;

import java.util.List;

public interface BoardGameService {
    @Transactional(readOnly = true)
    List<BoardGameSummaryDto> getAllBoardGames();

    @Transactional
    BoardGameDto addBoardGame(CreateAndUpdateBoardGameDto createBoardGameDto);

    @Transactional(readOnly = true)
    BoardGameDto getBoardGameById(Long id);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    BoardGameDto updateBoardGame(Long id, CreateAndUpdateBoardGameDto updateBoardGameDto);

    @Transactional
    void deleteBoardGame(Long id);

    @Transactional(readOnly = true)
    List<BoardGameSummaryDto> filterBoardGames(FilterBoardGamesRequestDto filterBoardGamesRequestDto);

    @Transactional(readOnly = true)
    List<BoardGameSummaryDto> searchBoardgames(String searchValue);
}
