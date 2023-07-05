package ua.rivnegray.boardgames_shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ShoppingCartDto;

import java.util.List;
import java.util.Set;

public interface ShoppingCartService {
    @Transactional(readOnly = true)
    ShoppingCartDto getShoppingCart(long cartId);

    @Transactional
    ShoppingCartDto clearShoppingCart(long cartId);

    @Transactional
    ShoppingCartDto addProductToShoppingCart(long cartId, AddProductInShoppingCartDto addProductInShoppingCartDto);

    @Transactional(readOnly = true)
    List<ProductInShoppingCartDto> getProductsInShoppingCart(long cartId);

    @Transactional
    ShoppingCartDto removeProductFromShoppingCart(long cartId, long productInCartId);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    ShoppingCartDto updateQuantityOfProductInShoppingCart(long cartId, long productInCartId, UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto);
}
