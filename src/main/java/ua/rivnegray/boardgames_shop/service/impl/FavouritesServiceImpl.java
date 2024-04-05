package ua.rivnegray.boardgames_shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInFavouritesDto;
import ua.rivnegray.boardgames_shop.model.BaseEntity;
import ua.rivnegray.boardgames_shop.model.FavouriteProduct;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.FavouriteProductRepository;
import ua.rivnegray.boardgames_shop.service.FavouritesService;
import ua.rivnegray.boardgames_shop.service.TokenExtractorService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouritesServiceImpl implements FavouritesService {

    private final TokenExtractorService tokenExtractorService;

    private final FavouriteProductRepository favouriteProductRepository;

    private final BoardGameRepository boardGameRepository;

    @Override
    public void mapFavourites(List<MapProductInFavouritesDto> productInFavouritesDtos) {
        Set<Long> favouritesIds = getCurrentUserFavourites()
                .stream().map(FavouriteProduct::getBoardGame)
                .map(BaseEntity::getId)
                .collect(Collectors.toUnmodifiableSet());

        List<FavouriteProduct> newFavourites = productInFavouritesDtos.stream()
                .map(MapProductInFavouritesDto::productId)
                .filter(id -> !favouritesIds.contains(id))
                .map(boardGameRepository::findById)
                .flatMap(Optional::stream)
                .map(boardGame -> FavouriteProduct.builder()
                        .boardGame(boardGame)
                        .user(tokenExtractorService.extractCurrentUser())
                        .build())
                .toList();

        favouriteProductRepository.saveAll(newFavourites);
    }

    private List<FavouriteProduct> getCurrentUserFavourites() {
        return favouriteProductRepository.findAllByUserId(tokenExtractorService.extractCurrentUserId());
    }
}
