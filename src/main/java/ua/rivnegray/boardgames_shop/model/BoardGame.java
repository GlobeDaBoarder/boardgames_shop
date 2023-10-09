package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardGame extends Product {

    @Builder()
    public BoardGame(String manufacturer, String productName, String productNameInEnglish, String productDescription, BigDecimal productPrice,
                     Integer productQuantityInStock, Set<String> productImageURLs, ProductCategory productCategory,
                     String productCode, String gameSet, Set<BoardGameType> gameTypes, Set<BoardGameGenre> gameGenres,
                     Set<BoardGameMechanic> gameMechanics, Integer minAge, Integer minPlayers, Integer maxPlayers,
                     Integer minGameDuration, Integer maxGameDuration,
                     String author, String illustrator, BoardGameLanguage gameLanguage, String BGGLink) {
        super(manufacturer, productName, productNameInEnglish, productDescription, productPrice, productQuantityInStock, productImageURLs , productCategory);
        this.productCode = productCode;
        this.gameSet = gameSet;
        this.gameTypes = gameTypes;
        this.gameGenres = gameGenres;
        this.gameMechanics = gameMechanics;
        this.minAge = minAge;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.minGameDuration = minGameDuration;
        this.maxGameDuration = maxGameDuration;
        this.gameLanguage = gameLanguage;
        this.author = author;
        this.illustrator = illustrator;
        this.BGGLink = BGGLink;
    }

    /**
     *
     * <b><i>ENG:</i></b>The product's Stock Keeping Unit (SKU), an identifier provided by the manufacturer.<br>
     * <b><i>RU/UA:</i></b> Артикул
     */
    private String productCode;

    /**
     * The game set / gae configuration of a board game: <br>
     * Components of a board game, that come in the box, included in the package
     * <b><i>RU/UA:</i></b> Комплектация
     */
    @Column(length = 1000)
    private String gameSet;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "board_game_types")
    private Set<BoardGameType> gameTypes = new HashSet<>();

    @ManyToMany
    private Set<BoardGameGenre> gameGenres = new HashSet<>();

    @ManyToMany
    private Set<BoardGameMechanic> gameMechanics = new HashSet<>();

    private Integer minAge;

    private Integer minPlayers;

    private Integer maxPlayers;

    private Integer minGameDuration;

    private Integer maxGameDuration;

    private String author;

    private String illustrator;

    @Enumerated(EnumType.STRING)
    private BoardGameLanguage gameLanguage;

    /**
     * <b><i>ENG:</i></b> The link to the board game's page on BoardGameGeek.com<br>
     */
    @URL
    private String BGGLink;
}
