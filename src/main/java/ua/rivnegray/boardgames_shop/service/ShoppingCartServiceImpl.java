package ua.rivnegray.boardgames_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ShoppingCartDto;
import ua.rivnegray.boardgames_shop.exceptions.ShoppingCartIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.ProductMapper;
import ua.rivnegray.boardgames_shop.mapper.ShoppingCartMapper;
import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;
import ua.rivnegray.boardgames_shop.model.ShoppingCart;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.ProductInShoppingCartRepository;
import ua.rivnegray.boardgames_shop.repository.ShoppingCartRepository;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;

import java.util.List;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;
    ProductInShoppingCartRepository productInShoppingCartRepository;
    ShoppingCartMapper shoppingCartMapper;
    ProductMapper productMapper;
    BoardGameRepository boardGameRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ShoppingCartMapper shoppingCartMapper,
                                   ProductInShoppingCartRepository productInShoppingCartRepository,
                                   ProductMapper productMapper,
                                   BoardGameRepository boardGameRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartMapper = shoppingCartMapper;
        this.productInShoppingCartRepository = productInShoppingCartRepository;
        this.productMapper = productMapper;
        this.boardGameRepository = boardGameRepository;
    }

    private ShoppingCart fetchShoppingCartFromRepo(long cartId) {
        return this.shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(cartId));
    }

    @Override
    public ShoppingCartDto getShoppingCart(long cartId) {
        return this.shoppingCartRepository.findById(cartId)
                .map(shoppingCart -> this.shoppingCartMapper.toShoppingCartDto(shoppingCart))
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(cartId));
    }

    @Override
    public ShoppingCartDto clearShoppingCart(long cartId) {
        ShoppingCart shoppingCart = fetchShoppingCartFromRepo(cartId);
        shoppingCart.getProductsInShoppingCart().clear();
        this.shoppingCartRepository.save(shoppingCart);
        return this.shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto addProductToShoppingCart(long cartId, AddProductInShoppingCartDto addProductInShoppingCartDto) {
        ProductInShoppingCart productInShoppingCart = this.productMapper.toProductInShoppingCart(cartId,
                addProductInShoppingCartDto, this.boardGameRepository, this.shoppingCartRepository);
        ShoppingCart shoppingCart = fetchShoppingCartFromRepo(cartId);
        shoppingCart.getProductsInShoppingCart().add(productInShoppingCart);
        this.shoppingCartRepository.save(shoppingCart);
        return this.shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public List<ProductInShoppingCartDto> getProductsInShoppingCart(long cartId) {
        ShoppingCart shoppingCart = fetchShoppingCartFromRepo(cartId);
        return shoppingCart.getProductsInShoppingCart().stream()
                .map(productInShoppingCart -> this.productMapper.toProductInShoppingCartDto(productInShoppingCart))
                .toList();
    }

    @Override
    public ShoppingCartDto removeProductFromShoppingCart(long cartId, long productInCartId) {
        ShoppingCart shoppingCart = fetchShoppingCartFromRepo(cartId);
        Set<ProductInShoppingCart> productsInShoppingCart = shoppingCart.getProductsInShoppingCart();
        productsInShoppingCart.removeIf(productInShoppingCart -> productInShoppingCart.getId() == productInCartId);
        this.shoppingCartRepository.save(shoppingCart);
        return this.shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto updateQuantityOfProductInShoppingCart(long cartId, long productInCartId, UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto) {
        ProductInShoppingCart productInShoppingCart = this.productInShoppingCartRepository.findById(productInCartId)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(productInCartId));
        productInShoppingCart.setQuantity(updateQuantityOfProductInShoppingCartDto.quantity());
        this.productInShoppingCartRepository.save(productInShoppingCart);
        return getShoppingCart(cartId);
    }
}
