package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductImage extends BaseEntity{

    public ProductImage(Product product, String imageURL) {
        this.product = product;
        this.imageURL = imageURL;
    }
    @ManyToOne
    private Product product;
    private String originalFileName;
    private String imagePath;
    private String imageURL;
}
