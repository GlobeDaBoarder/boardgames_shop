package ua.rivnegray.boardgames_shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.AddressDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ShoppingCartDto;

import java.util.List;
import java.util.Set;

public interface ShoppingCartService {
    // admin operations
    @Transactional(readOnly = true)
    List<ProductInShoppingCartDto> getProductsInShoppingCart(Long cartId);

    // current user  operations
    @Transactional
    ShoppingCartDto clearMyShoppingCart();

    @Transactional
    ShoppingCartDto addProductToMyShoppingCart(Long productId);

    @Transactional(readOnly = true)
    List<ProductInShoppingCartDto> getProductsInMyShoppingCart();

    @Transactional
    ShoppingCartDto removeProductFromMyShoppingCart(Long productInCartId);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    ShoppingCartDto updateQuantityOfProductInMyShoppingCart(Long productInCartId,
                                     UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto);

    @Transactional
    OrderDto checkoutUnregisteredUser(AddAndUpdateAddressDto addressDto);
    
    @Transactional
    OrderDto checkoutRegisteredUser(Long addressId);
}
