package ua.rivnegray.boardgames_shop.delegateService;

import generated.shopping_cart.api.ShoppingCartApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ShoppingCartDto;
import ua.rivnegray.boardgames_shop.service.ShoppingCartService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ShoppingCartApiDelegateImpl implements ShoppingCartApiDelegate {

    ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartApiDelegateImpl(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ShoppingCartApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<ShoppingCartDto> addProductToShoppingCart(Long cartId, AddProductInShoppingCartDto addProductInShoppingCartDto) {
        ShoppingCartDto shoppingCartDto = this.shoppingCartService.addProductToShoppingCart(cartId, addProductInShoppingCartDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(shoppingCartDto.id())
                .toUri();

        return ResponseEntity.created(location).body(shoppingCartDto);
    }

    @Override
    public ResponseEntity<ShoppingCartDto> clearShoppingCart(Long cartId) {
        this.shoppingCartService.clearShoppingCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProductInShoppingCartDto>> getProductsInShoppingCart(Long cartId) {
        return ResponseEntity.ok(this.shoppingCartService.getProductsInShoppingCart(cartId));
    }

    @Override
    public ResponseEntity<ShoppingCartDto> getShoppingCart(Long cartId) {
        return ResponseEntity.ok(this.shoppingCartService.getShoppingCart(cartId));
    }

    @Override
    public ResponseEntity<ShoppingCartDto> removeProductFromShoppingCart(Long cartId, Long productInCartId) {
        this.shoppingCartService.removeProductFromShoppingCart(cartId, productInCartId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ShoppingCartDto> updateQuantityOfProductInShoppingCart(Long cartId,
                                                                                 Long productInCartId,
                                                                                 UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto) {
        return ResponseEntity.ok(this.shoppingCartService.updateQuantityOfProductInShoppingCart(cartId,
                productInCartId, updateQuantityOfProductInShoppingCartDto));
    }
}
