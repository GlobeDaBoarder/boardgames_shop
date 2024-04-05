package generated.favourites.api;

import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInFavouritesDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link FavouritesApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public interface FavouritesApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /favourites/map : Map favourites from one stored in local storage to one stored in database
     * This operation is used when a user decides to log in or register. Mapping of favourites from local storage to database should occur automatically upon login.
     *
     * @param mapProductInFavouritesDto  (required)
     * @return Favourites mapped successfully (status code 200)
     *         or Invalid favourites data (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     * @see FavouritesApi#mapFavourites
     */
    default ResponseEntity<Void> mapFavourites(List<@Valid MapProductInFavouritesDto> mapProductInFavouritesDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
