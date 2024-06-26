package generated.user.api;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInFavouritesDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateEmailDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateNameAndSurnameDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePasswordDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePhoneDto;
import ua.rivnegray.boardgames_shop.DTO.response.*;

import java.util.List;
import java.util.Optional;

/**
 * A delegate to be called by the {@link UsersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public interface UsersApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /users/me/address : Add an address to me
     *
     * @param addAndUpdateAddressDto  (required)
     * @return address added (status code 201)
     * @see UsersApi#addAddress
     */
    default ResponseEntity<UserPublicDto> addAddress(AddAndUpdateAddressDto addAndUpdateAddressDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /users/me/favourites/{productId} : Add a product to my favourites
     *
     * @param productId  (required)
     * @return product added to favourites (status code 201)
     *         or product not found (status code 404)
     *         or product already in favourites (status code 409)
     *         or Unauthorized (status code 401)
     * @see UsersApi#addProductToFavourites
     */
    default ResponseEntity<FavouriteProductCreationResponseDto> addProductToFavourites(Long productId) {
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

    /**
     * POST /users : Create a new user with specified roles
     *
     * @param createAnyUserDto  (required)
     * @return User created (status code 201)
     * @see UsersApi#createSpecifiedUser
     */
    default ResponseEntity<UserPublicDto> createSpecifiedUser(CreateAnyUserDto createAnyUserDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /users/me : Delete me
     *
     * @return User deleted (status code 204)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     * @see UsersApi#deleteMe
     */
    default ResponseEntity<Void> deleteMe() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/me/address : Get all my addresses
     *
     * @return All addresses were retrieved successfully (status code 200)
     * @see UsersApi#getAllMyAddresses
     */
    default ResponseEntity<List<AddressDto>> getAllMyAddresses() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/me/favourites : Get all my favourite products
     *
     * @return All favourite products were retrieved successfully (status code 200)
     *         or Unauthorized (status code 401)
     * @see UsersApi#getAllMyFavouriteProducts
     */
    default ResponseEntity<List<FavouriteProductDto>> getAllMyFavouriteProducts() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : true }, { \"emptyValue\" : true } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/roles : Get all user roles
     *
     * @return successful operation (status code 200)
     * @see UsersApi#getAllUserRoles
     */
    default ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users : Get all users
     *
     * @return successful operation (status code 200)
     * @see UsersApi#getAllUsersPublicInfo
     */
    default ResponseEntity<List<UserPublicDto>> getAllUsersPublicInfo() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }, { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/me : Get my info
     *
     * @return successful operation (status code 200)
     *         or Unauthorized (status code 401)
     * @see UsersApi#getMe
     */
    default ResponseEntity<UserPublicDto> getMe() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/me/address/{addressId} : Get a my address by address id
     *
     * @param addressId  (required)
     * @return The address was found (status code 200)
     * @see UsersApi#getMyAddressById
     */
    default ResponseEntity<AddressDto> getMyAddressById(Long addressId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/{userId} : Get a user by ID
     *
     * @param userId  (required)
     * @return successful operation (status code 200)
     *         or User not found (status code 404)
     * @see UsersApi#getUserPublicInfoById
     */
    default ResponseEntity<UserPublicDto> getUserPublicInfoById(Long userId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/role/{role} : Get users by role
     *
     * @param role  (required)
     * @return Users wih role found (status code 200)
     * @see UsersApi#getUsersByRole
     */
    default ResponseEntity<List<UserPublicDto>> getUsersByRole(String role) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }, { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /users/{userId} : hard delete a user by ID
     *
     * @param userId  (required)
     * @return User deleted (status code 204)
     *         or User not found (status code 404)
     * @see UsersApi#hardDeleteUser
     */
    default ResponseEntity<Void> hardDeleteUser(Long userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /users/me/favourites/map : Map favourites from one stored in local storage to one stored in database
     * This operation is used when a user decides to log in or register. Mapping of favourites from local storage to database should occur automatically upon login.
     *
     * @param mapProductInFavouritesDto  (required)
     * @return Favourites mapped successfully (status code 200)
     *         or Invalid favourites data (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     * @see UsersApi#mapFavourites
     */
    default ResponseEntity<Void> mapFavourites(List<@Valid MapProductInFavouritesDto> mapProductInFavouritesDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /users/me/address/{addressId} : Remove my address
     *
     * @param addressId  (required)
     * @return address deleted (status code 204)
     *         or address not found (status code 404)
     * @see UsersApi#removeAddress
     */
    default ResponseEntity<Void> removeAddress(Long addressId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /users/me/favourites : Remove all my favourite products
     *
     * @return all favourite products removed (status code 204)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     * @see UsersApi#removeAllMyFavouriteProducts
     */
    default ResponseEntity<Void> removeAllMyFavouriteProducts() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /users/me/favourites/{favoriteId} : Remove a product from my favourites
     *
     * @param favoriteId  (required)
     * @return product removed from favourites (status code 204)
     *         or product not found in favourites (status code 404)
     *         or Unauthorized (status code 401)
     * @see UsersApi#removeProductFromFavourites
     */
    default ResponseEntity<Void> removeProductFromFavourites(Long favoriteId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /users/me/address/{addressId} : Update my address
     * Fields of the request body that are not specified will be ignored. So you can update only the fields you need.
     *
     * @param addressId  (required)
     * @param addAndUpdateAddressDto  (required)
     * @return address updated (status code 200)
     * @see UsersApi#updateMyAddress
     */
    default ResponseEntity<UserPublicDto> updateMyAddress(Long addressId,
        AddAndUpdateAddressDto addAndUpdateAddressDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /users/me/email : Update a  my email
     *
     * @param updateEmailDto  (required)
     * @return email updated (status code 200)
     * @see UsersApi#updateMyEmail
     */
    default ResponseEntity<UserPublicDto> updateMyEmail(UpdateEmailDto updateEmailDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /users/me/nameAndSurname : Update my name and surname
     *
     * @param updateNameAndSurnameDto  (required)
     * @return name and surname updated (status code 200)
     * @see UsersApi#updateMyNameAndSurname
     */
    default ResponseEntity<UserPublicDto> updateMyNameAndSurname(UpdateNameAndSurnameDto updateNameAndSurnameDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /users/me/password : Update my user&#39;s password
     *
     * @param updatePasswordDto  (required)
     * @return password updated (status code 200)
     * @see UsersApi#updateMyPassword
     */
    default ResponseEntity<UserPublicDto> updateMyPassword(UpdatePasswordDto updatePasswordDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /users/me/phone : Update my phone
     *
     * @param updatePhoneDto  (required)
     * @return phone number updated (status code 200)
     * @see UsersApi#updateMyPhone
     */
    default ResponseEntity<UserPublicDto> updateMyPhone(UpdatePhoneDto updatePhoneDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
