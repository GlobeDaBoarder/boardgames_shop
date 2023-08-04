package generated.session.api;

import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestWithMapShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link LoginApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-03T19:22:19.463312616+03:00[Europe/Kiev]")
public interface LoginApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /login : Log in a user
     *
     * @param loginRequestWithMapShoppingCartDto  (required)
     * @return Successful operation (status code 200)
     *         or Bad Request (status code 400)
     *         or Conflict, e.g., email already in use (status code 409)
     * @see LoginApi#loginUser
     */
    default ResponseEntity<LoginResponseDto> loginUser(LoginRequestWithMapShoppingCartDto loginRequestWithMapShoppingCartDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"user\" : { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }, \"token\" : \"token\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
