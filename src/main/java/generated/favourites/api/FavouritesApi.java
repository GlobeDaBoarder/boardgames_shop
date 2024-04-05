/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package generated.favourites.api;

import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInFavouritesDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Generated;
import org.springframework.security.access.prepost.PreAuthorize;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
@Validated
@Tag(name = "favourites", description = "the favourites API")
public interface FavouritesApi {

    default FavouritesApiDelegate getDelegate() {
        return new FavouritesApiDelegate() {};
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
     */
    @Operation(
        operationId = "mapFavourites",
        summary = "Map favourites from one stored in local storage to one stored in database",
        description = "This operation is used when a user decides to log in or register. Mapping of favourites from local storage to database should occur automatically upon login.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Favourites mapped successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid favourites data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/favourites/map",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> mapFavourites(
        @Parameter(name = "MapProductInFavouritesDto", description = "", required = true) @Valid @RequestBody List<@Valid MapProductInFavouritesDto> mapProductInFavouritesDto
    ) {
        return getDelegate().mapFavourites(mapProductInFavouritesDto);
    }

}
