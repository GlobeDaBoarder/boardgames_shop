package ua.rivnegray.boardgames_shop.delegateService;

import generated.board_game.api.BoardgamesApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameSummaryDto;
import ua.rivnegray.boardgames_shop.DTO.response.CatalogResponseDto;
import ua.rivnegray.boardgames_shop.DTO.response.MinMaxDto;
import ua.rivnegray.boardgames_shop.model.SortType;
import ua.rivnegray.boardgames_shop.service.BoardGameGenreService;
import ua.rivnegray.boardgames_shop.service.BoardGameMechanicService;
import ua.rivnegray.boardgames_shop.service.BoardGameService;

import java.net.URI;
import java.util.List;

@Service
public class    BoardgamesApiDelegateImpl implements BoardgamesApiDelegate {

    private final BoardGameService boardGameService;

    private final BoardGameGenreService boardGameGenreService;

    private final BoardGameMechanicService boardGameMechanicService;

    @Autowired
    BoardgamesApiDelegateImpl(BoardGameService boardGameService,
                                     BoardGameGenreService boardGameGenreService,
                                     BoardGameMechanicService boardGameMechanicService) {
        this.boardGameService = boardGameService;
        this.boardGameGenreService = boardGameGenreService;
        this.boardGameMechanicService = boardGameMechanicService;
    }

    @Override
    public ResponseEntity<BoardGameDto> addBoardGame(CreateAndUpdateBoardGameDto createAndUpdateBoardGameDto) {
        BoardGameDto boardGameDto = boardGameService.addBoardGame(createAndUpdateBoardGameDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(boardGameDto.id())
                .toUri();

        return ResponseEntity.created(location).body(boardGameDto);
    }

    @Override
    public ResponseEntity<Void> deleteBoardGame(Long id) {
        this.boardGameService.deleteBoardGame(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CatalogResponseDto> getAllBoardGames(String search, String filter, SortType sort, Integer page) {
        return ResponseEntity.ok(this.boardGameService.getAllBoardGames(search, filter, sort, page));
    }

    @Override
    public ResponseEntity<BoardGameDto> getBoardGameById(Long id) {
        return ResponseEntity.ok(this.boardGameService.getBoardGameById(id));
    }

    @Override
    public ResponseEntity<BoardGameDto> updateBoardGame(Long id, CreateAndUpdateBoardGameDto createAndUpdateBoardGameDto) {
        return ResponseEntity.ok(this.boardGameService.updateBoardGame(id, createAndUpdateBoardGameDto));
    }

    @Override
    public ResponseEntity<BoardGameGenreDto> addGenre(CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto) {
        BoardGameGenreDto boardGameGenreDto = this.boardGameGenreService.addGenre(createAndUpdateBoardGameGenreDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(boardGameGenreDto.id())
                .toUri();

        return ResponseEntity.created(location).body(boardGameGenreDto);
    }

    @Override
    public ResponseEntity<BoardGameMechanicDto> addMechanic(CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto) {
        BoardGameMechanicDto boardGameMechanicDto = this.boardGameMechanicService.addMechanic(createAndUpdateBoardGameMechanicDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(boardGameMechanicDto.id())
                .toUri();

        return ResponseEntity.created(location).body(boardGameMechanicDto);
    }

    @Override
    public ResponseEntity<Void> deleteGenre(Long id) {
        this.boardGameGenreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteMechanic(Long id) {
        this.boardGameMechanicService.deleteMechanic(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<BoardGameGenreDto>> getAllGenres() {
        return ResponseEntity.ok(this.boardGameGenreService.getAllGenres());
    }

    @Override
    public ResponseEntity<List<BoardGameMechanicDto>> getAllMechanics() {
        return ResponseEntity.ok(this.boardGameMechanicService.getAllMechanics());
    }

    @Override
    public ResponseEntity<BoardGameGenreDto> getGenreById(Long id) {
        return ResponseEntity.ok(this.boardGameGenreService.getGenreById(id));

    }

    @Override
    public ResponseEntity<BoardGameMechanicDto> getMechanicById(Long id) {
        return ResponseEntity.ok(this.boardGameMechanicService.getMechanicById(id));
    }

    @Override
    public ResponseEntity<BoardGameGenreDto> updateGenre(Long id, CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto) {
        return ResponseEntity.ok(this.boardGameGenreService.updateGenre(id, createAndUpdateBoardGameGenreDto));
    }

    @Override
    public ResponseEntity<BoardGameMechanicDto> updateMechanic(Long id, CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto) {
        return ResponseEntity.ok(this.boardGameMechanicService.updateMechanic(id, createAndUpdateBoardGameMechanicDto));
    }

    @Override
    public ResponseEntity<BoardGameDto> archiveBoardGame(Long id) {
        return ResponseEntity.ok(this.boardGameService.archiveBoardGame(id));
    }

    @Override
    public ResponseEntity<List<BoardGameSummaryDto>> getAllArchivedBoardGames() {
        return ResponseEntity.ok(this.boardGameService.getAllArchivedBoardGames());
    }

    @Override
    public ResponseEntity<BoardGameDto> unarchiveBoardGame(Long id) {
        return ResponseEntity.ok(this.boardGameService.unarchiveBoardGame(id));
    }

    @Override
    public ResponseEntity<BoardGameDto> uploadAndAddImage(Long id, MultipartFile file) {
        return ResponseEntity.ok(this.boardGameService.uploadAndAddImage(id, file));
    }

    @Override
    public ResponseEntity<Resource> getBoardGameImage(String filename) {
        return ResponseEntity.ok()
                .contentType(this.boardGameService.getFilenameMediaType(filename))
                .body(this.boardGameService.getBoardGameImage(filename));
    }

    @Override
    public ResponseEntity<MinMaxDto> getGameDurationBounds() {
        return ResponseEntity.ok(this.boardGameService.getGameDurationBounds());
    }

    @Override
    public ResponseEntity<MinMaxDto> getPriceBounds() {
        return ResponseEntity.ok(this.boardGameService.getPriceBounds());
    }
}
