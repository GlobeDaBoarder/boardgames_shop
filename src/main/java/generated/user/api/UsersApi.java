/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package generated.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInFavouritesDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateEmailDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateNameAndSurnameDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePasswordDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePhoneDto;
import ua.rivnegray.boardgames_shop.DTO.response.*;

import java.util.List;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
@Validated
@Tag(name = "users", description = "the users API")
public interface UsersApi {

    default UsersApiDelegate getDelegate() {
        return new UsersApiDelegate() {};
    }

    /**
     * POST /users/me/address : Add an address to me
     *
     * @param addAndUpdateAddressDto  (required)
     * @return address added (status code 201)
     */
    @Operation(
        operationId = "addAddress",
        summary = "Add an address to me",
        responses = {
            @ApiResponse(responseCode = "201", description = "address added", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/users/me/address",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UserPublicDto> addAddress(
        @Parameter(name = "AddAndUpdateAddressDto", description = "", required = true) @Valid @RequestBody AddAndUpdateAddressDto addAndUpdateAddressDto
    ) {
        return getDelegate().addAddress(addAndUpdateAddressDto);
    }


    /**
     * POST /users/me/favourites/{productId} : Add a product to my favourites
     *
     * @param productId  (required)
     * @return product added to favourites (status code 201)
     *         or product not found (status code 404)
     *         or product already in favourites (status code 409)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "addProductToFavourites",
        summary = "Add a product to my favourites",
        responses = {
            @ApiResponse(responseCode = "201", description = "product added to favourites", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = FavouriteProductCreationResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "product not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "product already in favourites", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/users/me/favourites/{productId}",
        produces = { "application/json" }
    )
    default ResponseEntity<FavouriteProductCreationResponseDto> addProductToFavourites(
        @Parameter(name = "productId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("productId") Long productId
    ) {
        return getDelegate().addProductToFavourites(productId);
    }


    /**
     * POST /users : Create a new user with specified roles
     *
     * @param createAnyUserDto  (required)
     * @return User created (status code 201)
     */
    @Operation(
        operationId = "createSpecifiedUser",
        summary = "Create a new user with specified roles",
        responses = {
            @ApiResponse(responseCode = "201", description = "User created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:create')")
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/users",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UserPublicDto> createSpecifiedUser(
        @Parameter(name = "CreateAnyUserDto", description = "", required = true) @Valid @RequestBody CreateAnyUserDto createAnyUserDto
    ) {
        return getDelegate().createSpecifiedUser(createAnyUserDto);
    }


    /**
     * DELETE /users/me : Delete me
     *
     * @return User deleted (status code 204)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     */
    @Operation(
        operationId = "deleteMe",
        summary = "Delete me",
        responses = {
            @ApiResponse(responseCode = "204", description = "User deleted", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/users/me"
    )
    default ResponseEntity<Void> deleteMe(
        
    ) {
        return getDelegate().deleteMe();
    }


    /**
     * GET /users/me/address : Get all my addresses
     *
     * @return All addresses were retrieved successfully (status code 200)
     */
    @Operation(
        operationId = "getAllMyAddresses",
        summary = "Get all my addresses",
        responses = {
            @ApiResponse(responseCode = "200", description = "All addresses were retrieved successfully", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AddressDto.class)))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:readMe')")
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users/me/address",
        produces = { "application/json" }
    )
    default ResponseEntity<List<AddressDto>> getAllMyAddresses(
        
    ) {
        return getDelegate().getAllMyAddresses();
    }


    /**
     * GET /users/me/favourites : Get all my favourite products
     *
     * @return All favourite products were retrieved successfully (status code 200)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "getAllMyFavouriteProducts",
        summary = "Get all my favourite products",
        responses = {
            @ApiResponse(responseCode = "200", description = "All favourite products were retrieved successfully", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FavouriteProductDto.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:readMe')")
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users/me/favourites",
        produces = { "application/json" }
    )
    default ResponseEntity<List<FavouriteProductDto>> getAllMyFavouriteProducts(
        
    ) {
        return getDelegate().getAllMyFavouriteProducts();
    }


    /**
     * GET /users/roles : Get all user roles
     *
     * @return successful operation (status code 200)
     */
    @Operation(
        operationId = "getAllUserRoles",
        summary = "Get all user roles",
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserRoleDto.class)))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:read')")
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users/roles",
        produces = { "application/json" }
    )
    default ResponseEntity<List<UserRoleDto>> getAllUserRoles(
        
    ) {
        return getDelegate().getAllUserRoles();
    }


    /**
     * GET /users : Get all users
     *
     * @return successful operation (status code 200)
     */
    @Operation(
        operationId = "getAllUsersPublicInfo",
        summary = "Get all users",
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserPublicDto.class)))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:read')")
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users",
        produces = { "application/json" }
    )
    default ResponseEntity<List<UserPublicDto>> getAllUsersPublicInfo(
        
    ) {
        return getDelegate().getAllUsersPublicInfo();
    }


    /**
     * GET /users/me : Get my info
     *
     * @return successful operation (status code 200)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "getMe",
        summary = "Get my info",
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:readMe')")
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users/me",
        produces = { "application/json" }
    )
    default ResponseEntity<UserPublicDto> getMe(
        
    ) {
        return getDelegate().getMe();
    }


    /**
     * GET /users/me/address/{addressId} : Get a my address by address id
     *
     * @param addressId  (required)
     * @return The address was found (status code 200)
     */
    @Operation(
        operationId = "getMyAddressById",
        summary = "Get a my address by address id",
        responses = {
            @ApiResponse(responseCode = "200", description = "The address was found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:readMe')")
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users/me/address/{addressId}",
        produces = { "application/json" }
    )
    default ResponseEntity<AddressDto> getMyAddressById(
        @Parameter(name = "addressId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("addressId") Long addressId
    ) {
        return getDelegate().getMyAddressById(addressId);
    }


    /**
     * GET /users/{userId} : Get a user by ID
     *
     * @param userId  (required)
     * @return successful operation (status code 200)
     *         or User not found (status code 404)
     */
    @Operation(
        operationId = "getUserPublicInfoById",
        summary = "Get a user by ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:read')")
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users/{userId}",
        produces = { "application/json" }
    )
    default ResponseEntity<UserPublicDto> getUserPublicInfoById(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId
    ) {
        return getDelegate().getUserPublicInfoById(userId);
    }


    /**
     * GET /users/role/{role} : Get users by role
     *
     * @param role  (required)
     * @return Users wih role found (status code 200)
     */
    @Operation(
        operationId = "getUsersByRole",
        summary = "Get users by role",
        responses = {
            @ApiResponse(responseCode = "200", description = "Users wih role found", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserPublicDto.class)))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:read')")
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users/role/{role}",
        produces = { "application/json" }
    )
    default ResponseEntity<List<UserPublicDto>> getUsersByRole(
        @Parameter(name = "role", description = "", required = true, in = ParameterIn.PATH) @PathVariable("role") String role
    ) {
        return getDelegate().getUsersByRole(role);
    }


    /**
     * DELETE /users/{userId} : hard delete a user by ID
     *
     * @param userId  (required)
     * @return User deleted (status code 204)
     *         or User not found (status code 404)
     */
    @Operation(
        operationId = "hardDeleteUser",
        summary = "hard delete a user by ID",
        responses = {
            @ApiResponse(responseCode = "204", description = "User deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:delete')")
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/users/{userId}"
    )
    default ResponseEntity<Void> hardDeleteUser(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId
    ) {
        return getDelegate().hardDeleteUser(userId);
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
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/users/me/favourites/map",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> mapFavourites(
        @Parameter(name = "MapProductInFavouritesDto", description = "", required = true) @Valid @RequestBody List<@Valid MapProductInFavouritesDto> mapProductInFavouritesDto
    ) {
        return getDelegate().mapFavourites(mapProductInFavouritesDto);
    }


    /**
     * DELETE /users/me/address/{addressId} : Remove my address
     *
     * @param addressId  (required)
     * @return address deleted (status code 204)
     *         or address not found (status code 404)
     */
    @Operation(
        operationId = "removeAddress",
        summary = "Remove my address",
        responses = {
            @ApiResponse(responseCode = "204", description = "address deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "address not found", content = @Content)
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/users/me/address/{addressId}"
    )
    default ResponseEntity<Void> removeAddress(
        @Parameter(name = "addressId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("addressId") Long addressId
    ) {
        return getDelegate().removeAddress(addressId);
    }


    /**
     * DELETE /users/me/favourites : Remove all my favourite products
     *
     * @return all favourite products removed (status code 204)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     */
    @Operation(
        operationId = "removeAllMyFavouriteProducts",
        summary = "Remove all my favourite products",
        responses = {
            @ApiResponse(responseCode = "204", description = "all favourite products removed", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/users/me/favourites"
    )
    default ResponseEntity<Void> removeAllMyFavouriteProducts(
        
    ) {
        return getDelegate().removeAllMyFavouriteProducts();
    }


    /**
     * DELETE /users/me/favourites/{favoriteId} : Remove a product from my favourites
     *
     * @param favoriteId  (required)
     * @return product removed from favourites (status code 204)
     *         or product not found in favourites (status code 404)
     *         or Unauthorized (status code 401)
     */
    @Operation(
        operationId = "removeProductFromFavourites",
        summary = "Remove a product from my favourites",
        responses = {
            @ApiResponse(responseCode = "204", description = "product removed from favourites", content = @Content),
            @ApiResponse(responseCode = "404", description = "product not found in favourites", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/users/me/favourites/{favoriteId}"
    )
    default ResponseEntity<Void> removeProductFromFavourites(
        @Parameter(name = "favoriteId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("favoriteId") Long favoriteId
    ) {
        return getDelegate().removeProductFromFavourites(favoriteId);
    }


    /**
     * PATCH /users/me/address/{addressId} : Update my address
     * Fields of the request body that are not specified will be ignored. So you can update only the fields you need.
     *
     * @param addressId  (required)
     * @param addAndUpdateAddressDto  (required)
     * @return address updated (status code 200)
     */
    @Operation(
        operationId = "updateMyAddress",
        summary = "Update my address",
        description = "Fields of the request body that are not specified will be ignored. So you can update only the fields you need.",
        responses = {
            @ApiResponse(responseCode = "200", description = "address updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/users/me/address/{addressId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UserPublicDto> updateMyAddress(
        @Parameter(name = "addressId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("addressId") Long addressId,
        @Parameter(name = "AddAndUpdateAddressDto", description = "", required = true) @Valid @RequestBody AddAndUpdateAddressDto addAndUpdateAddressDto
    ) {
        return getDelegate().updateMyAddress(addressId, addAndUpdateAddressDto);
    }


    /**
     * PATCH /users/me/email : Update a  my email
     *
     * @param updateEmailDto  (required)
     * @return email updated (status code 200)
     */
    @Operation(
        operationId = "updateMyEmail",
        summary = "Update a  my email",
        responses = {
            @ApiResponse(responseCode = "200", description = "email updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/users/me/email",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UserPublicDto> updateMyEmail(
        @Parameter(name = "UpdateEmailDto", description = "", required = true) @Valid @RequestBody UpdateEmailDto updateEmailDto
    ) {
        return getDelegate().updateMyEmail(updateEmailDto);
    }


    /**
     * PATCH /users/me/nameAndSurname : Update my name and surname
     *
     * @param updateNameAndSurnameDto  (required)
     * @return name and surname updated (status code 200)
     */
    @Operation(
        operationId = "updateMyNameAndSurname",
        summary = "Update my name and surname",
        responses = {
            @ApiResponse(responseCode = "200", description = "name and surname updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/users/me/nameAndSurname",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UserPublicDto> updateMyNameAndSurname(
        @Parameter(name = "UpdateNameAndSurnameDto", description = "", required = true) @Valid @RequestBody UpdateNameAndSurnameDto updateNameAndSurnameDto
    ) {
        return getDelegate().updateMyNameAndSurname(updateNameAndSurnameDto);
    }


    /**
     * PATCH /users/me/password : Update my user&#39;s password
     *
     * @param updatePasswordDto  (required)
     * @return password updated (status code 200)
     */
    @Operation(
        operationId = "updateMyPassword",
        summary = "Update my user's password",
        responses = {
            @ApiResponse(responseCode = "200", description = "password updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/users/me/password",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UserPublicDto> updateMyPassword(
        @Parameter(name = "UpdatePasswordDto", description = "", required = true) @Valid @RequestBody UpdatePasswordDto updatePasswordDto
    ) {
        return getDelegate().updateMyPassword(updatePasswordDto);
    }


    /**
     * PATCH /users/me/phone : Update my phone
     *
     * @param updatePhoneDto  (required)
     * @return phone number updated (status code 200)
     */
    @Operation(
        operationId = "updateMyPhone",
        summary = "Update my phone",
        responses = {
            @ApiResponse(responseCode = "200", description = "phone number updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @PreAuthorize("hasAuthority('SCOPE_user:updateMe')")
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/users/me/phone",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UserPublicDto> updateMyPhone(
        @Parameter(name = "UpdatePhoneDto", description = "", required = true) @Valid @RequestBody UpdatePhoneDto updatePhoneDto
    ) {
        return getDelegate().updateMyPhone(updatePhoneDto);
    }

}
