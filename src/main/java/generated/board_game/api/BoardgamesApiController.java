package generated.board_game.api;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
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
