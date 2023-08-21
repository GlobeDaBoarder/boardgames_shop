package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
