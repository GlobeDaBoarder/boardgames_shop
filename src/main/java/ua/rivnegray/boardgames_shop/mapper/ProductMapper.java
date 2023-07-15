package ua.rivnegray.boardgames_shop.mapper;

import org.hibernate.annotations.Source;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.AddressDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserProfileDto;
import ua.rivnegray.boardgames_shop.exceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.ShoppingCartIdNotFoundException;
import ua.rivnegray.boardgames_shop.model.Address;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.Product;
import ua.rivnegray.boardgames_shop.model.ProductInOrder;
import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;
import ua.rivnegray.boardgames_shop.model.ShoppingCart;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.ShoppingCartRepository;
import ua.rivnegray.boardgames_shop.service.BoardGameService;
import ua.rivnegray.boardgames_shop.service.ShoppingCartService;
import ua.rivnegray.boardgames_shop.service.ShoppingCartServiceImpl;

@Mapper(componentModel = "spring", uses = {BoardGameRepository.class, ShoppingCartRepository.class})
public interface ProductMapper {

//    @Named("cartIdToShoppingCart")
//    default ShoppingCart cartIdToShoppingCart(Long cartId, @Context ShoppingCartRepository shoppingCartRepository) {
//        return shoppingCartRepository.findById(cartId).orElseThrow(() -> new ShoppingCartIdNotFoundException(cartId));
//    }
//
//    @Named("boardGameIdToProduct")
//    default Product boardGameIdToProduct(AddProductInShoppingCartDto addProductInShoppingCartDto,
//                                         @Context BoardGameRepository boardGameRepository) {
//        Long productId = addProductInShoppingCartDto.productId();
//        return boardGameRepository.findById(productId).orElseThrow(() -> new BoardGameIdNotFoundException(productId));
//    }
//
//    @Mapping(target = "shoppingCart", source = "cartId", qualifiedByName = "cartIdToShoppingCart")
//    @Mapping(target = "product", source = "addProductInShoppingCartDto.productId", qualifiedByName = "boardGameIdToProduct")
//    @Mapping(target = "quantity", source = "addProductInShoppingCartDto.quantity")
//    ProductInShoppingCart toProductInShoppingCart(Long cartId, AddProductInShoppingCartDto addProductInShoppingCartDto,
//                                                  @Context BoardGameRepository boardGameRepository,
//                                                  @Context ShoppingCartRepository shoppingCartRepository);


    @Mapping(target = "productId", source = "product.id")
    ProductInShoppingCartDto toProductInShoppingCartDto(ProductInShoppingCart productInShoppingCart);

    default ProductInShoppingCart toProductInShoppingCart(long cartId, AddProductInShoppingCartDto addProductInShoppingCartDto,
                                                  BoardGameRepository boardGameRepository,
                                                          ShoppingCartRepository shoppingCartRepository){
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(cartId));

        BoardGame boardGame = boardGameRepository.findById(addProductInShoppingCartDto.productId())
                .orElseThrow(() -> new BoardGameIdNotFoundException(addProductInShoppingCartDto.productId()));

        return new ProductInShoppingCart(boardGame, shoppingCart, addProductInShoppingCartDto.quantity());
    }


    @Mapping(target = "order", ignore = true)
    ProductInOrder toProductInOrder(ProductInShoppingCart productInShoppingCart);

    @Mapping(target = "productId", source = "product.id")
    ProductInOrderDto toProductInOrderDto(ProductInOrder productInOrder);
}

