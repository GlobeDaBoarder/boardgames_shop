package generated.board_game.api;

import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateBoardGameDto;


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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-13T10:49:05.379974125+03:00[Europe/Kiev]")
@Controller
@RequestMapping("${openapi.boardgames.base-path:}")
public class BoardgamesApiController implements BoardgamesApi {

    private final BoardgamesApiDelegate delegate;

    public BoardgamesApiController(@Autowired(required = false) BoardgamesApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new BoardgamesApiDelegate() {});
    }

    @Override
    public BoardgamesApiDelegate getDelegate() {
        return delegate;
    }

}
