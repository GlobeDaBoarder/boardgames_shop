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
    @Transactional(readOnly = true)
    ShoppingCartDto getShoppingCart(Long cartId);

    @Transactional
    ShoppingCartDto clearShoppingCart(Long cartId);

    @Transactional
    ShoppingCartDto addProductToShoppingCart(Long cartId, AddProductInShoppingCartDto addProductInShoppingCartDto);

    @Transactional(readOnly = true)
    List<ProductInShoppingCartDto> getProductsInShoppingCart(Long cartId);

    @Transactional
    ShoppingCartDto removeProductFromShoppingCart(Long cartId, Long productInCartId);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    ShoppingCartDto updateQuantityOfProductInShoppingCart(Long cartId, Long productInCartId,
                                                          UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto);

    @Transactional
    OrderDto checkoutUnregisteredUser(Long cartId, AddAndUpdateAddressDto addressDto);
    
    @Transactional
    OrderDto checkoutRegisteredUser(Long cartId, Long addressId);
}
