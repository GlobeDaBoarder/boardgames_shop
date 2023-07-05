package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {BoardGameGenreRepository.class, BoardGameMechanicRepository.class,
        BoardGameGenreMapper.class, BoardGameMechanicMapper.class})
public interface BoardGameMapper {
    @Named("genreIdsToGenres")
    default Set<BoardGameGenre> genreIdsToGenres(Set<Long> genreIds, @Context BoardGameGenreRepository boardGameGenreRepository) {
        Set<BoardGameGenre> genres = new HashSet<>();
        for (Long genreId : genreIds) {
            genres.add(boardGameGenreRepository.findById(genreId).get());
        }
        return genres;
    }

    @Named("mechanicIdsToMechanics")
    default Set<BoardGameMechanic> mechanicIdsToMechanics(Set<Long> mechanicIds, @Context BoardGameMechanicRepository boardGameMechanicRepository) {
        Set<BoardGameMechanic> mechanics = new HashSet<>();
        for (Long mechanicId : mechanicIds) {
            mechanics.add(boardGameMechanicRepository.findById(mechanicId).get());
        }
        return mechanics;
    }

    BoardGameDto boardGameToBoardGameDto(BoardGame boardGame);

    @Mapping(target = "gameGenres", source = "gameGenreIds", qualifiedByName = "genreIdsToGenres")
    @Mapping(target = "gameMechanics", source = "gameMechanicIds", qualifiedByName = "mechanicIdsToMechanics")
    BoardGame createBoardGameDtoToBoardGame(CreateAndUpdateBoardGameDto createAndUpdateBoardGameDto,
                                            @Context BoardGameGenreRepository boardGameGenreRepository,
                                            @Context BoardGameMechanicRepository boardGameMechanicRepository);

    @Mapping(target = "gameGenres", source = "gameGenreIds", qualifiedByName = "genreIdsToGenres")
    @Mapping(target = "gameMechanics", source = "gameMechanicIds", qualifiedByName = "mechanicIdsToMechanics")
    void updateBoardGameFromDto(CreateAndUpdateBoardGameDto dto, @MappingTarget BoardGame game,
                                @Context BoardGameGenreRepository boardGameGenreRepository,
                                @Context BoardGameMechanicRepository boardGameMechanicRepository);

}
