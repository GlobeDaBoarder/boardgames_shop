/**
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package generated.user.api;

import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.response.AddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateEmailDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePasswordDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePhoneDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateUsernameDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserPublicDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserRoleDto;
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

    import javax.validation.Valid;
    import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.springframework.security.access.prepost.PreAuthorize;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-09T22:15:02.014073813+03:00[Europe/Kiev]")
    @Validated
    @Tag(name = "users", description = "the users API")
    public interface UsersApi {

                default UsersApiDelegate getDelegate() {
                return new UsersApiDelegate() {};
                }

            /**
            * POST /users/{userId}/address : Add an address to a user
            *
                * @param userId  (required)
                * @param addAndUpdateAddressDto  (required)
            * @return address added (status code 201)
            */
                @Operation(
                operationId = "addAddress",
                    summary = "Add an address to a user",
                responses = {
                    @ApiResponse(responseCode = "201", description = "address added", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('user:write')")
            @RequestMapping(
            method = RequestMethod.POST,
            value = "/users/{userId}/address",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<UserPublicDto> addAddress(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId,
        @Parameter(name = "AddAndUpdateAddressDto", description = "", required = true) @Valid @RequestBody AddAndUpdateAddressDto addAndUpdateAddressDto
            ) {
            return getDelegate().addAddress(userId, addAndUpdateAddressDto);
            }


            /**
            * POST /users/availability/email : Check if email is available
            *
                * @param updateEmailDto  (required)
            * @return checked for availability of email successfully (status code 204)
                *         or email already taken (status code 409)
            */
                @Operation(
                operationId = "checkEmailAvailability",
                    summary = "Check if email is available",
                responses = {
                    @ApiResponse(responseCode = "204", description = "checked for availability of email successfully", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))
                    }),
                    @ApiResponse(responseCode = "409", description = "email already taken", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('admin:read')")
            @RequestMapping(
            method = RequestMethod.POST,
            value = "/users/availability/email",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<Boolean> checkEmailAvailability(
        @Parameter(name = "UpdateEmailDto", description = "", required = true) @Valid @RequestBody UpdateEmailDto updateEmailDto
            ) {
            return getDelegate().checkEmailAvailability(updateEmailDto);
            }


            /**
            * POST /users/availability/username : Check if username is available
            *
                * @param updateUsernameDto  (required)
            * @return checked for availability of username successfully (status code 204)
                *         or username already taken (status code 409)
            */
                @Operation(
                operationId = "checkUsernameAvailability",
                    summary = "Check if username is available",
                responses = {
                    @ApiResponse(responseCode = "204", description = "checked for availability of username successfully", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))
                    }),
                    @ApiResponse(responseCode = "409", description = "username already taken", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('admin:read')")
            @RequestMapping(
            method = RequestMethod.POST,
            value = "/users/availability/username",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<Boolean> checkUsernameAvailability(
        @Parameter(name = "UpdateUsernameDto", description = "", required = true) @Valid @RequestBody UpdateUsernameDto updateUsernameDto
            ) {
            return getDelegate().checkUsernameAvailability(updateUsernameDto);
            }


            /**
            * POST /users/customer : Create a new customer user
            *
                * @param createCustomerUserDto  (required)
            * @return User created (status code 201)
            */
                @Operation(
                operationId = "createCustomerUser",
                    summary = "Create a new customer user",
                responses = {
                    @ApiResponse(responseCode = "201", description = "User created", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('user:write')")
            @RequestMapping(
            method = RequestMethod.POST,
            value = "/users/customer",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<UserPublicDto> createCustomerUser(
        @Parameter(name = "CreateCustomerUserDto", description = "", required = true) @Valid @RequestBody CreateCustomerUserDto createCustomerUserDto
            ) {
            return getDelegate().createCustomerUser(createCustomerUserDto);
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
                }
                )
                        @PreAuthorize("hasAuthority('admin:write')")
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
            * DELETE /users/{userId} : Delete a user by ID
            *
                * @param userId  (required)
            * @return User deleted (status code 204)
                *         or User not found (status code 404)
            */
                @Operation(
                operationId = "deleteUser",
                    summary = "Delete a user by ID",
                responses = {
                    @ApiResponse(responseCode = "204", description = "User deleted", content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
                }
                )
                        @PreAuthorize("hasAuthority('admin:write')")
            @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/users/{userId}"
            )
        default ResponseEntity<Void> deleteUser(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId
            ) {
            return getDelegate().deleteUser(userId);
            }


            /**
            * GET /users/{userId}/addresses/{addressId} : Get a user&#39;s address by address id
            *
                * @param userId  (required)
                * @param addressId  (required)
            * @return The address was found (status code 200)
            */
                @Operation(
                operationId = "getAddressById",
                    summary = "Get a user's address by address id",
                responses = {
                    @ApiResponse(responseCode = "200", description = "The address was found", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDto.class))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('user:read')")
            @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/{userId}/addresses/{addressId}",
            produces = { "application/json" }
            )
        default ResponseEntity<AddressDto> getAddressById(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId,
        @Parameter(name = "addressId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("addressId") Long addressId
            ) {
            return getDelegate().getAddressById(userId, addressId);
            }


            /**
            * GET /users/{userId}/addresses : Get all addresses of a user
            *
                * @param userId  (required)
            * @return All addresses were retrieved successfully (status code 200)
            */
                @Operation(
                operationId = "getAllAddresses",
                    summary = "Get all addresses of a user",
                responses = {
                    @ApiResponse(responseCode = "200", description = "All addresses were retrieved successfully", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AddressDto.class)))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('user:read')")
            @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/{userId}/addresses",
            produces = { "application/json" }
            )
        default ResponseEntity<List<AddressDto>> getAllAddresses(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId
            ) {
            return getDelegate().getAllAddresses(userId);
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
                }
                )
                        @PreAuthorize("hasAuthority('admin:read')")
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
                }
                )
                        @PreAuthorize("hasAuthority('admin:read')")
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
                }
                )
                        @PreAuthorize("hasAuthority('admin:read')")
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
            * @return Users wih role found (status code 204)
            */
                @Operation(
                operationId = "getUsersByRole",
                    summary = "Get users by role",
                responses = {
                    @ApiResponse(responseCode = "204", description = "Users wih role found", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserPublicDto.class)))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('admin:read')")
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
            * DELETE /users/{userId}/address/{addressId} : Remove an address from a user
            *
                * @param userId  (required)
                * @param addressId  (required)
            * @return address deleted (status code 204)
                *         or address not founf (status code 404)
            */
                @Operation(
                operationId = "removeAddress",
                    summary = "Remove an address from a user",
                responses = {
                    @ApiResponse(responseCode = "204", description = "address deleted", content = @Content),
                    @ApiResponse(responseCode = "404", description = "address not founf", content = @Content)
                }
                )
                        @PreAuthorize("hasAuthority('user:write')")
            @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/users/{userId}/address/{addressId}"
            )
        default ResponseEntity<Void> removeAddress(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId,
        @Parameter(name = "addressId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("addressId") Long addressId
            ) {
            return getDelegate().removeAddress(userId, addressId);
            }


            /**
            * PATCH /users/{userId}/address/{addressId} : Update a user&#39;s address
            *
                * @param userId  (required)
                * @param addressId  (required)
                * @param addAndUpdateAddressDto  (required)
            * @return address updated (status code 200)
            */
                @Operation(
                operationId = "updateAddress",
                    summary = "Update a user's address",
                responses = {
                    @ApiResponse(responseCode = "200", description = "address updated", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('user:write')")
            @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/users/{userId}/address/{addressId}",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<UserPublicDto> updateAddress(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId,
        @Parameter(name = "addressId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("addressId") Long addressId,
        @Parameter(name = "AddAndUpdateAddressDto", description = "", required = true) @Valid @RequestBody AddAndUpdateAddressDto addAndUpdateAddressDto
            ) {
            return getDelegate().updateAddress(userId, addressId, addAndUpdateAddressDto);
            }


            /**
            * PATCH /users/{userId}/email : Update a user&#39;s email
            *
                * @param userId  (required)
                * @param updateEmailDto  (required)
            * @return email updated (status code 200)
            */
                @Operation(
                operationId = "updateEmail",
                    summary = "Update a user's email",
                responses = {
                    @ApiResponse(responseCode = "200", description = "email updated", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('user:write')")
            @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/users/{userId}/email",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<UserPublicDto> updateEmail(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId,
        @Parameter(name = "UpdateEmailDto", description = "", required = true) @Valid @RequestBody UpdateEmailDto updateEmailDto
            ) {
            return getDelegate().updateEmail(userId, updateEmailDto);
            }


            /**
            * PATCH /users/{userId}/password : Update a user&#39;s password
            *
                * @param userId  (required)
                * @param updatePasswordDto  (required)
            * @return password updated (status code 200)
            */
                @Operation(
                operationId = "updatePassword",
                    summary = "Update a user's password",
                responses = {
                    @ApiResponse(responseCode = "200", description = "password updated", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('user:write')")
            @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/users/{userId}/password",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<UserPublicDto> updatePassword(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId,
        @Parameter(name = "UpdatePasswordDto", description = "", required = true) @Valid @RequestBody UpdatePasswordDto updatePasswordDto
            ) {
            return getDelegate().updatePassword(userId, updatePasswordDto);
            }


            /**
            * PATCH /users/{userId}/phone : Update a user&#39;s phone
            *
                * @param userId  (required)
                * @param updatePhoneDto  (required)
            * @return phone number updated (status code 200)
            */
                @Operation(
                operationId = "updatePhone",
                    summary = "Update a user's phone",
                responses = {
                    @ApiResponse(responseCode = "200", description = "phone number updated", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('user:write')")
            @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/users/{userId}/phone",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<UserPublicDto> updatePhone(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId,
        @Parameter(name = "UpdatePhoneDto", description = "", required = true) @Valid @RequestBody UpdatePhoneDto updatePhoneDto
            ) {
            return getDelegate().updatePhone(userId, updatePhoneDto);
            }


            /**
            * PATCH /users/{userId}/username : Update a user&#39;s username
            *
                * @param userId  (required)
                * @param updateUsernameDto  (required)
            * @return username updated (status code 200)
            */
                @Operation(
                operationId = "updateUsername",
                    summary = "Update a user's username",
                responses = {
                    @ApiResponse(responseCode = "200", description = "username updated", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserPublicDto.class))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('user:write')")
            @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/users/{userId}/username",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<UserPublicDto> updateUsername(
        @Parameter(name = "userId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId,
        @Parameter(name = "UpdateUsernameDto", description = "", required = true) @Valid @RequestBody UpdateUsernameDto updateUsernameDto
            ) {
            return getDelegate().updateUsername(userId, updateUsernameDto);
            }

        }
