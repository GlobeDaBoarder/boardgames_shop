package ua.rivnegray.boardgames_shop.repository.specifications;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;
import ua.rivnegray.boardgames_shop.model.BoardGameLanguage;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;

import java.math.BigDecimal;
import java.util.Set;

public class BoardGameSpecification {
    public static Specification<BoardGame> hasManufacturers(Set<String> manufacturers) {
        return (root, query, cb) -> {
            if (manufacturers == null || manufacturers.isEmpty()) {
                return null;
            }
            return root.get("manufacturer").in(manufacturers);
        };
    }

    public static Specification<BoardGame> hasPriceInRange(BigDecimal minProductPrice, BigDecimal maxProductPrice){
        return (root, query, cb) -> {
            if (minProductPrice == null && maxProductPrice == null) {
                return null;
            }
            if (minProductPrice == null) {
                return cb.lessThanOrEqualTo(root.get("productPrice"), maxProductPrice);
            }
            if (maxProductPrice == null) {
                return cb.greaterThanOrEqualTo(root.get("productPrice"), minProductPrice);
            }
            return cb.between(root.get("productPrice"), minProductPrice, maxProductPrice);
        };
    }

    public static Specification<BoardGame> hasBoardGameGenres(Set<String> boardGameGenres) {
        return (root, query, cb) -> {
            if (boardGameGenres == null || boardGameGenres.isEmpty()) {
                return null;
            }
            Join<BoardGame, BoardGameGenre> join = root.join("gameGenres");
            return join.get("genreName").in(boardGameGenres);
        };
    }

    public static Specification<BoardGame> hasBoardGameMechanics(Set<String> boardGameMechanics) {
        return (root, query, cb) -> {
            if (boardGameMechanics == null || boardGameMechanics.isEmpty()) {
                return null;
            }
            Join<BoardGame, BoardGameMechanic> join = root.join("gameMechanics");
            return join.get("mechanicName").in(boardGameMechanics);
        };
    }

    public static Specification<BoardGame> hasMinAges(Set<Integer> minAges){
        return (root, query, cb) -> {
            if (minAges == null || minAges.isEmpty()) {
                return null;
            }
            return root.get("minAge").in(minAges);
        };
    }

    public static Specification<BoardGame> hasPlayersInRange(Set<Integer> playerCounts){
        return (root, query, cb) -> {
            if (playerCounts == null || playerCounts.isEmpty()) {
                return null;
            }
            Predicate minPlayersIn = root.get("minPlayers").in(playerCounts);
            Predicate maxPlayersIn = root.get("maxPlayers").in(playerCounts);
            return cb.or(minPlayersIn, maxPlayersIn);
        };
    }

    public static Specification<BoardGame> hasGameDurationInRange(Integer minGameDurationFilter, Integer maxGameDurationFilter) {
        return (root, query, cb) -> {
            if (minGameDurationFilter == null && maxGameDurationFilter == null) {
                return null;
            }
            if (minGameDurationFilter == null) {
                return cb.lessThanOrEqualTo(root.get("minGameDuration"), maxGameDurationFilter);
            }
            if (maxGameDurationFilter == null) {
                return cb.greaterThanOrEqualTo(root.get("maxGameDuration"), minGameDurationFilter);
            }

            Predicate gameMinInRange = cb.between(root.get("minGameDuration"), minGameDurationFilter, maxGameDurationFilter);
            Predicate gameMaxInRange = cb.between(root.get("maxGameDuration"), minGameDurationFilter, maxGameDurationFilter);
            Predicate filterWithinGameRange = cb.and(
                    cb.lessThanOrEqualTo(root.get("minGameDuration"), minGameDurationFilter),
                    cb.greaterThanOrEqualTo(root.get("maxGameDuration"), maxGameDurationFilter)
            );

            return cb.or(gameMinInRange, gameMaxInRange, filterWithinGameRange);
        };
    }

    public static Specification<BoardGame> hasLanguage(Set<BoardGameLanguage> boardGameLanguages){
        return (root, query, cb) -> {
            if (boardGameLanguages == null || boardGameLanguages.isEmpty()) {
                return null;
            }
            return root.get("gameLanguage").in(boardGameLanguages);
        };
    }
}
