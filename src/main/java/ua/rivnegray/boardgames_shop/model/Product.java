package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product extends BaseEntity{

    @Column(unique = true)
    private String manufacturer;

    @Column(unique = true, nullable = false)
    private String productName;

    @Column(length = 1000)
    private String productDescription;

    // todo add a discount system later
    @Column(nullable = false)
    private BigDecimal productPrice;

    @Column(nullable = false)
    private Integer productQuantityInStock;

    // todo figure out storing images
    private String productImageURL;

    private ProductCategory productCategory;
}
