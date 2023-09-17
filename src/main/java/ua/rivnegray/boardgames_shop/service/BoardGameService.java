package ua.rivnegray.boardgames_shop.service;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameSummaryDto;
import ua.rivnegray.boardgames_shop.DTO.response.CatalogResponseDto;
import ua.rivnegray.boardgames_shop.DTO.response.MinMaxDto;
import ua.rivnegray.boardgames_shop.model.SortType;

import java.util.List;

public interface BoardGameService {
    @Transactional(readOnly = true)
    CatalogResponseDto getAllBoardGames(String search, String filterDTO, SortType sort, Integer page);

    @Transactional
    BoardGameDto addBoardGame(CreateAndUpdateBoardGameDto createBoardGameDto);

    @Transactional(readOnly = true)
    BoardGameDto getBoardGameById(Long id);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    BoardGameDto updateBoardGame(Long id, CreateAndUpdateBoardGameDto updateBoardGameDto);

    @Transactional
    void deleteBoardGame(Long id);

    @Transactional(readOnly = true)
    List<BoardGameSummaryDto> getAllArchivedBoardGames();

    @Transactional
    BoardGameDto archiveBoardGame(Long id);

    @Transactional
    BoardGameDto unarchiveBoardGame(Long id);

    @Transactional
    BoardGameDto uploadAndAddImage(Long id, MultipartFile imageFile);

    @Transactional
    Resource getBoardGameImage(String filename);

    String extractImageExtensionFromFilename(String filename);

    MediaType getFilenameMediaType(String filename);

    @Transactional(readOnly = true)
    MinMaxDto getGameDurationBounds();

    @Transactional(readOnly = true)
    MinMaxDto getPriceBounds();
}
