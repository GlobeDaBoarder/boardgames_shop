package ua.rivnegray.boardgames_shop.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInCartCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;

import java.util.List;

public interface ShoppingCartService {
    // admin operations
    @Transactional(readOnly = true)
    List<ProductInShoppingCartDto> getProductsInShoppingCart(Long cartId);

    // current user  operations
    @Transactional
    void clearMyShoppingCart();

    @Transactional
    List<ProductInShoppingCartDto> addProductToMyShoppingCart(Long productId);

    @Transactional(readOnly = true)
    List<ProductInShoppingCartDto> getProductsInMyShoppingCart();

    @Transactional
    List<ProductInShoppingCartDto> removeProductFromMyShoppingCart(Long productInCartId);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    List<ProductInShoppingCartDto> updateQuantityOfProductInMyShoppingCart(Long productInCartId,
                                     UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto);

    @Transactional
    OrderDto checkoutMyUser(Long addressId);

    @Transactional
    List<ProductInShoppingCartDto> mapCart(List<MapProductInCartCartDto> mapShoppingCartDto);
}
