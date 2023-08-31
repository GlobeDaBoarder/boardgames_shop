package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostRemove;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions.ImageFileDeleteException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(indexes = {@Index(name = "product_image_hash_index", columnList = "imageHash")})
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
    private String imageHash;

    @PostRemove
    public void deleteImageFile(){
        try {
            if(this.imagePath != null)
                Files.delete(Path.of(this.imagePath));
        } catch (IOException e) {
            throw new ImageFileDeleteException(this.imagePath, e);
        }
    }
}
