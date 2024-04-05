package generated.session.api;

import ua.rivnegray.boardgames_shop.DTO.request.RegisterCustomerRequestDto;
import ua.rivnegray.boardgames_shop.DTO.response.TokenDto;
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
 * A delegate to be called by the {@link RegisterApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public interface RegisterApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /register : Register a new user
     *
     * @param registerCustomerRequestDto  (required)
     * @return Registration successful (status code 201)
     *         or Bad Request (status code 400)
     *         or Conflict, e.g., email already in use (status code 409)
     * @see RegisterApi#registerUser
     */
    default ResponseEntity<TokenDto> registerUser(RegisterCustomerRequestDto registerCustomerRequestDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
