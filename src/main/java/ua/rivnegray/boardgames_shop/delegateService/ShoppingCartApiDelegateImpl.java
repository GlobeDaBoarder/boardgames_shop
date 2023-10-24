package ua.rivnegray.boardgames_shop.delegateService;

import generated.shopping_cart.api.ShoppingCartApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInCartCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.service.ShoppingCartService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartApiDelegateImpl implements ShoppingCartApiDelegate {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartApiDelegateImpl(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ShoppingCartApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<List<ProductInShoppingCartDto>> addProductToMyShoppingCart(Long productId) {
        return ResponseEntity.ok(this.shoppingCartService.addProductToMyShoppingCart(productId));
    }

    @Override
    public ResponseEntity<Void> clearMyShoppingCart() {
        this.shoppingCartService.clearMyShoppingCart();
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProductInShoppingCartDto>> getProductsInMyShoppingCart() {
        return ResponseEntity.ok(this.shoppingCartService.getProductsInMyShoppingCart());
    }

    @Override
    public ResponseEntity<List<ProductInShoppingCartDto>> getProductsInShoppingCart(Long cartId) {
        return ResponseEntity.ok(this.shoppingCartService.getProductsInShoppingCart(cartId));
    }

    @Override
    public ResponseEntity<List<ProductInShoppingCartDto>> removeProductFromMyShoppingCart(Long productInCartId) {
        return ResponseEntity.ok(this.shoppingCartService.removeProductFromMyShoppingCart(productInCartId));
    }



    @Override
    public ResponseEntity<List<ProductInShoppingCartDto>> updateQuantityOfProductInMyShoppingCart(Long productInCartId,
                                UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto) {
        return ResponseEntity.ok(this.shoppingCartService.updateQuantityOfProductInMyShoppingCart(productInCartId,
                updateQuantityOfProductInShoppingCartDto));
    }

    @Override
    public ResponseEntity<OrderDto> checkoutRegisteredUser(Long addressId) {
        OrderDto orderDto = this.shoppingCartService.checkoutMyUser(addressId);

        URI location = ServletUriComponentsBuilder
                .fromUriString("orders/me")
                .path("/{id}")
                .buildAndExpand(orderDto.id())
                .toUri();

        return ResponseEntity.created(location).body(orderDto);
    }

    @Override
    public ResponseEntity<List<ProductInShoppingCartDto>> mapCart(List<MapProductInCartCartDto> mapShoppingCartDto) {
        return ResponseEntity.ok(this.shoppingCartService.mapCart(mapShoppingCartDto));
    }
}
