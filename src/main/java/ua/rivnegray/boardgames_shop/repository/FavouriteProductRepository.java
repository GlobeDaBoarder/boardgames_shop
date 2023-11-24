package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.FavouriteProduct;

import java.util.List;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Long>{
    List<FavouriteProduct> findAllByUserId(Long userId);

    boolean existsByUserIdAndBoardGameId(Long id, Long productId);
}
