/**
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package generated.session.api;

import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestDto;
import ua.rivnegray.boardgames_shop.DTO.response.LoginResponseDto;
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


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-16T19:33:24.139362263+03:00[Europe/Kiev]")
    @Validated
    @Tag(name = "login", description = "the login API")
    public interface LoginApi {

                default LoginApiDelegate getDelegate() {
                return new LoginApiDelegate() {};
                }

            /**
            * POST /login : Log in a user
            *
                * @param loginRequestDto  (required)
            * @return Successful operation (status code 200)
                *         or Bad Request (status code 400)
                *         or Conflict, e.g., email already in use (status code 409)
            */
                @Operation(
                operationId = "loginUser",
                    summary = "Log in a user",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Conflict, e.g., email already in use", content = @Content)
                }
                )
                        //allow all
            @RequestMapping(
            method = RequestMethod.POST,
            value = "/login",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<LoginResponseDto> loginUser(
        @Parameter(name = "LoginRequestDto", description = "", required = true) @Valid @RequestBody LoginRequestDto loginRequestDto
            ) {
            return getDelegate().loginUser(loginRequestDto);
            }

        }
