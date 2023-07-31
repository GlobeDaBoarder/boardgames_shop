package generated.shopping_cart.api;

import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;


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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T11:35:27.614962630+03:00[Europe/Kiev]")
@Controller
@RequestMapping("${openapi.shoppingCart.base-path:}")
public class ShoppingCartApiController implements ShoppingCartApi {

    private final ShoppingCartApiDelegate delegate;

    public ShoppingCartApiController(@Autowired(required = false) ShoppingCartApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ShoppingCartApiDelegate() {});
    }

    @Override
    public ShoppingCartApiDelegate getDelegate() {
        return delegate;
    }

}
