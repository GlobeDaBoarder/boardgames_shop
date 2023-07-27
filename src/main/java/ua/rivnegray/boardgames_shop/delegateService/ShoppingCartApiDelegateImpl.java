package ua.rivnegray.boardgames_shop.delegateService;

import generated.shopping_cart.api.ShoppingCartApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
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
    public ResponseEntity<ShoppingCartDto> addProductToMyShoppingCart(Long productId) {
        ShoppingCartDto shoppingCartDto = this.shoppingCartService.addProductToMyShoppingCart(productId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(shoppingCartDto.id())
                .toUri();

        return ResponseEntity.created(location).body(shoppingCartDto);
    }

    @Override
    public ResponseEntity<ShoppingCartDto> clearMyShoppingCart() {
        return ResponseEntity.ok(this.shoppingCartService.clearMyShoppingCart());
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
    public ResponseEntity<ShoppingCartDto> removeProductFromMyShoppingCart(Long productInCartId) {
        return ResponseEntity.ok(this.shoppingCartService.removeProductFromMyShoppingCart(productInCartId));
    }



    @Override
    public ResponseEntity<ShoppingCartDto> updateQuantityOfProductInMyShoppingCart(Long productInCartId,
                                UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto) {
        return ResponseEntity.ok(this.shoppingCartService.updateQuantityOfProductInMyShoppingCart(productInCartId,
                updateQuantityOfProductInShoppingCartDto));
    }

    @Override
    public ResponseEntity<OrderDto> checkoutUnregisteredUser( AddAndUpdateAddressDto addressDto) {
//        return ResponseEntity.ok(this.shoppingCartService.checkoutUnregisteredUser(addressDto));
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<OrderDto> checkoutRegisteredUser(Long addressId) {
        return ResponseEntity.ok(this.shoppingCartService.checkoutRegisteredUser(addressId));
    }


}
