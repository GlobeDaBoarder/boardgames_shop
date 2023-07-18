package generated.session.api;

import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.response.LoginResponseDto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-18T15:40:43.238188873+03:00[Europe/Kiev]")
@Controller
@RequestMapping("${openapi.title.base-path:}")
public class RegisterApiController implements RegisterApi {

    private final RegisterApiDelegate delegate;

    public RegisterApiController(@Autowired(required = false) RegisterApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new RegisterApiDelegate() {});
    }

    @Override
    public RegisterApiDelegate getDelegate() {
        return delegate;
    }

}
