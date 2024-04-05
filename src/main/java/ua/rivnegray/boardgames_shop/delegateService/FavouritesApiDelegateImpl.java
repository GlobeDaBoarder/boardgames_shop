package ua.rivnegray.boardgames_shop.delegateService;

import generated.favourites.api.FavouritesApiDelegate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInFavouritesDto;
import ua.rivnegray.boardgames_shop.service.FavouritesService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavouritesApiDelegateImpl implements FavouritesApiDelegate {

    private final FavouritesService favouritesService;

    @Override
    public ResponseEntity<Void> mapFavourites(List<@Valid MapProductInFavouritesDto> mapProductInFavouritesDto) {
        favouritesService.mapFavourites(mapProductInFavouritesDto);
        return ResponseEntity.ok().build();
    }
}
