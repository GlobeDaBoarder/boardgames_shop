package ua.rivnegray.boardgames_shop.mapper;

import jdk.jfr.Name;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameSummaryDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameGenreIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameMechanicIdNotFoundException;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;
import ua.rivnegray.boardgames_shop.model.ProductImage;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {BoardGameGenreRepository.class, BoardGameMechanicRepository.class,
        BoardGameGenreMapper.class, BoardGameMechanicMapper.class})
public interface BoardGameMapper {
    @Named("genreIdsToGenres")
    default Set<BoardGameGenre> genreIdsToGenres(Set<Long> genreIds, @Context BoardGameGenreRepository boardGameGenreRepository) {
        Set<BoardGameGenre> genres = new HashSet<>();
        for (Long genreId : genreIds) {
            genres.add(boardGameGenreRepository.findById(genreId)
                    .orElseThrow(() -> new BoardGameGenreIdNotFoundException(genreId)));
        }
        return genres;
    }

    @Named("mechanicIdsToMechanics")
    default Set<BoardGameMechanic> mechanicIdsToMechanics(Set<Long> mechanicIds, @Context BoardGameMechanicRepository boardGameMechanicRepository) {
        Set<BoardGameMechanic> mechanics = new HashSet<>();
        for (Long mechanicId : mechanicIds) {
            mechanics.add(boardGameMechanicRepository.findById(mechanicId)
                    .orElseThrow(() -> new BoardGameMechanicIdNotFoundException(mechanicId)));
        }
        return mechanics;
    }

    @Named("pickFirstImageURL")
    default String pickFirstImageURL(Set<ProductImage> productImages){
        return productImages.iterator().next().getImagePath();
    }

    @Named("mapProductImagesToImageURLs")
    default Set<String> mapProductImagesToImageURLs(Set<ProductImage> productImages){
        return productImages.stream()
                .map(ProductImage::getImagePath)
                .collect(Collectors.toSet());
    }


    @Mapping(source = "productImages", target = "productImageURLs", qualifiedByName = "mapProductImagesToImageURLs")
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

    @Mapping(source = "productImages", target = "productImageURL", qualifiedByName = "pickFirstImageURL")
    BoardGameSummaryDto boardGameToBoardGameSummaryDto(BoardGame boardGame);
}
