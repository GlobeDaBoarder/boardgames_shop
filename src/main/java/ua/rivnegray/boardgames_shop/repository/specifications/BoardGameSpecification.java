package ua.rivnegray.boardgames_shop.repository.specifications;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;
import ua.rivnegray.boardgames_shop.model.BoardGameLanguage;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;
import ua.rivnegray.boardgames_shop.model.BoardGameType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

    public static Specification<BoardGame> hasBoardGameTypes(Set<String> boardGameTypes) {
        return (root, query, cb) -> {
            if (boardGameTypes == null || boardGameTypes.isEmpty()) {
                return null;
            }

            // Create a subquery to select board games that have the specified types
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<BoardGame> subRoot = subquery.from(BoardGame.class);
            Join<Object, Object> subJoin = subRoot.join("gameTypes");
            subquery.select(subRoot.get("id"))
                    .where(subJoin.in(boardGameTypes.stream()
                            .map(BoardGameType::fromBoardGameTypeNameInUkrainian)
                            .toList()));

            // The main query will then filter board games based on the subquery results
            return root.get("id").in(subquery);
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

    public static Specification<BoardGame> hasMinAges(Set<String> ageIntervals){
        return (root, query, criteriaBuilder) -> {
            if (ageIntervals == null || ageIntervals.isEmpty()) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();
            for (String ageInterval : ageIntervals) {
                if (ageInterval.equals("18+")) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minAge"), 18));
                    continue;
                }
                String[] ageBoundValues = ageInterval.split("-", 2);
                Integer minBound = Integer.valueOf(ageBoundValues[0]);
                Integer maxBound = Integer.valueOf(ageBoundValues[1]);
                predicates.add(criteriaBuilder.between(root.get("minAge"), minBound, maxBound));
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<BoardGame> hasPlayersInRange(Set<String> playerCounts){
        return (root, query, criteriaBuilder) -> {
            if (playerCounts == null || playerCounts.isEmpty()) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();
            for (String playerCount : playerCounts) {
                if (playerCount.equals("Більше 6")) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("maxPlayers" ), 6));
                    continue;
                }
                predicates.add(criteriaBuilder.and(
                        criteriaBuilder.lessThanOrEqualTo(root.get("minPlayers"), Integer.valueOf(playerCount)),
                        criteriaBuilder.greaterThanOrEqualTo(root.get("maxPlayers"), Integer.valueOf(playerCount))
                ));
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<BoardGame> hasGameDurationInRange(Integer minGameDurationFilter, Integer maxGameDurationFilter){
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

    public static Specification<BoardGame> hasLanguage(Set<String> boardGameLanguageNamesInUkrainian){
        return (root, query, cb) -> {
            if (boardGameLanguageNamesInUkrainian == null || boardGameLanguageNamesInUkrainian.isEmpty()) {
                return null;
            }
            return root.get("gameLanguage").in(boardGameLanguageNamesInUkrainian.stream()
                    .map(BoardGameLanguage::fromLanguageNameInUkrainian)
                    .toList());
        };
    }

    public static Specification<BoardGame> hasIsRemovedFalse() {
        return (root, query, cb) -> cb.isFalse(root.get("isRemoved"));
    }
}
