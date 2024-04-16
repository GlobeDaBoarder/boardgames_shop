package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameSummaryDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameGenreIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameMechanicIdNotFoundException;
import ua.rivnegray.boardgames_shop.model.*;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class BoardGameMapperService {
    @Autowired
    private BoardGameMechanicRepository boardGameMechanicRepository;
    @Autowired
    private BoardGameGenreRepository boardGameGenreRepository;

    @Named("mechanicIdsToMechanics")
    Set<BoardGameMechanic> mechanicIdsToMechanics(Set<Long> mechanicIds) {
        if (mechanicIds == null)
            return null;

        return mechanicIds.stream()
                .map(id -> this.boardGameMechanicRepository.findById(id)
                        .orElseThrow(() -> new BoardGameMechanicIdNotFoundException(id)))
                .collect(Collectors.toSet());
    }

    @Named("genreIdsToGenres")
    Set<BoardGameGenre> genreIdsToGenres(Set<Long> genreIds) {
        if (genreIds == null)
            return null;

        return genreIds.stream()
                .map(id -> this.boardGameGenreRepository.findById(id)
                        .orElseThrow(() -> new BoardGameGenreIdNotFoundException(id)))
                .collect(Collectors.toSet());
    }

    @Named("gameTypeNamesToGameTypes")
    Set<BoardGameType> gameTypeNamesToGameTypes(Set<String> gameTypeNames) {
        if (gameTypeNames == null)
            return null;

        return gameTypeNames.stream()
                .map(BoardGameType::fromBoardGameTypeNameInUkrainian)
                .collect(Collectors.toSet());
    }

    @Named("mapProductImagesToImageURLs")
    Set<String> mapProductImagesToImageURLs(Set<ProductImage> productImages){
        return productImages.stream()
                .map(ProductImage::getImageURL)
                .collect(Collectors.toSet());
    }

    @Named("pickFirstImageURL")
    String pickFirstImageURL(Set<ProductImage> productImages){
        return productImages.iterator().next().getImageURL();
    }

    @Named("mapLanguageEnumToUkrString")
    String mapLanguageEnumToUkrString(BoardGameLanguage boardGameLanguage){return boardGameLanguage.getLanguageNameInUkrainian();}

    @Named("gameGameTypesToTypeNames")
    Set<String> gameGameTypesToTypeNames(Set<BoardGameType> boardGameTypes){
        return boardGameTypes.stream()
                .map(BoardGameType::getBoardGameTypeNameInUkrainian)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "gameTypes", source = "gameTypeNames", qualifiedByName = "gameTypeNamesToGameTypes")
    @Mapping(target = "gameMechanics", source = "gameMechanicIds", qualifiedByName = "mechanicIdsToMechanics")
    @Mapping(target = "gameGenres", source = "gameGenreIds", qualifiedByName = "genreIdsToGenres")
    @Mapping(target = "productCategory", constant = "BOARD_GAMES")
    public abstract BoardGame toBoardGame(CreateAndUpdateBoardGameDto createBoardGameDro);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "gameTypes", source = "gameTypeNames", qualifiedByName = "gameTypeNamesToGameTypes")
    @Mapping(target = "gameMechanics", source = "gameMechanicIds", qualifiedByName = "mechanicIdsToMechanics")
    @Mapping(target = "gameGenres", source = "gameGenreIds", qualifiedByName = "genreIdsToGenres")
    @Mapping(target = "productCategory", constant = "BOARD_GAMES")
    public abstract void updateBoardGame(@MappingTarget BoardGame boardGame, CreateAndUpdateBoardGameDto createBoardGameDto);

    @Mapping(source = "productImages", target = "productImageURLs", qualifiedByName = "mapProductImagesToImageURLs")
    @Mapping(source = "gameLanguage", target = "gameLanguage", qualifiedByName = "mapLanguageEnumToUkrString")
    @Mapping(source = "gameTypes", target = "gameTypes", qualifiedByName = "gameGameTypesToTypeNames")
    public abstract BoardGameDto boardGameToBoardGameDto(BoardGame boardGame);

    @Mapping(source = "productImages", target = "productImageURL", qualifiedByName = "pickFirstImageURL")
    public abstract BoardGameSummaryDto boardGameToBoardGameSummaryDto(BoardGame boardGame);
}
