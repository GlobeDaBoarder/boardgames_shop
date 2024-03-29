package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product extends BaseEntity{

    public Product(String manufacturer, String productName, String productNameInEnglishString,String productDescription, BigDecimal productPrice, Integer productQuantityInStock, Set<String> productImageURLs, ProductCategory productCategory) {
        this.manufacturer = manufacturer;
        this.productName = productName;
        this.productNameInEnglish = productNameInEnglishString;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantityInStock = productQuantityInStock;
        if (productImageURLs == null) {
            this.productImages = Set.of();
        }
        else {
            this.productImages = productImageURLs.stream()
                    .map(url -> new ProductImage(this, url))
                    .collect(Collectors.toSet());
        }
        this.productCategory = productCategory;
    }

    @Column(nullable = false)
    private String manufacturer;

    @Column(unique = true, nullable = false)
    private String productName;

    @Column(unique = true)
    private String productNameInEnglish;

    @Column(length = 1000, nullable = false)
    private String productDescription;

    // todo add a discount system later
    private BigDecimal productPrice;

    private Integer productQuantityInStock;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ProductImage> productImages;

    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductInShoppingCart> productInShoppingCarts;
}
