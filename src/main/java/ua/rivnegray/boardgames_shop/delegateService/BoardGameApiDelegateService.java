package ua.rivnegray.boardgames_shop.delegateService;

import generated.board_game.api.BoardgamesApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.service.BoardGameService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class BoardGameApiDelegateService implements BoardgamesApiDelegate {

    BoardGameService boardGameService;

    @Autowired
    public BoardGameApiDelegateService(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return BoardgamesApiDelegate.super.getRequest();
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
    public ResponseEntity<List<BoardGameDto>> getAllBoardGames() {
        return ResponseEntity.ok(this.boardGameService.getAllBoardGames());
    }

    @Override
    public ResponseEntity<BoardGameDto> getBoardGameById(Long id) {
        return ResponseEntity.ok(this.boardGameService.getBoardGameById(id));
    }

    @Override
    public ResponseEntity<BoardGameDto> updateBoardGame(Long id, CreateAndUpdateBoardGameDto createAndUpdateBoardGameDto) {
        return ResponseEntity.ok(this.boardGameService.updateBoardGame(id, createAndUpdateBoardGameDto));
    }
}
