package nure.pancake.game.shop.gameproductservice.search.criteria;

import jakarta.persistence.criteria.*;
import lombok.*;
import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import nure.pancake.game.shop.gameproductservice.entities.GenreEntity;
import nure.pancake.game.shop.gameproductservice.utils.Range;
import org.hibernate.sql.ast.tree.predicate.PredicateCollector;
import org.hibernate.sql.ast.tree.predicate.PredicateContainer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameSearchCriteria implements Specification<GameEntity> {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> gameIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> genreIds;
    @Singular(ignoreNullCollections = true)
    private Collection<String> genreNames;
    @Singular(ignoreNullCollections = true)
    private Collection<String> platforms;
    private Range<Float> price;
    private Range<Float> ageRating;
    private Range<LocalDate> releaseDate;
    private String name;

    @Override
    public Predicate toPredicate(Root<GameEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> criteria = new ArrayList<>();

        if (!CollectionUtils.isEmpty(gameIds)) {
            criteria.add(cb.in(root.
                    get("id")).value(gameIds));
        }
        if (!CollectionUtils.isEmpty(genreIds)) {
            criteria.add(cb.in(root
                            .get(GameEntity.FieldName.GENRE.getFieldName())
                            .get(GenreEntity.FieldName.GENRE_ID.getFieldName()))
                    .value(genreIds));
        }
        if (!CollectionUtils.isEmpty(genreNames)) {
            criteria.add(cb.in(root
                            .get(GameEntity.FieldName.GENRE.getFieldName())
                            .get(GenreEntity.FieldName.GENRE_NAME.getFieldName()))
                    .value(genreNames));
        }

        if (!CollectionUtils.isEmpty(platforms)) {
            var platformsPath = root.get("platforms").as(String.class);

            criteria.add(
                    cb.or(
                            platforms.stream()
                                    .map(p -> cb.like(platformsPath, "%" + p + "%"))
                                    .toArray(Predicate[]::new)
                    )
            );
        }


        if (StringUtils.hasText(name)) {
            criteria.add(cb.like(root.get("name"), "%" + name + "%"));
        }
        if (Range.hasFrom(price)) {
            criteria.add(
                    cb.ge(root.get("price"), price.fromMap(Float::parseFloat))
            );
        }
        if (Range.hasTo(price)) {
            criteria.add(
                    cb.le(root.get("price"), price.toMap(Float::parseFloat))
            );
        }
        if (Range.hasFrom(ageRating)) {
            criteria.add(
                    cb.ge(root.get("ageRating"), ageRating.fromMap(Float::parseFloat))
            );
        }
        if (Range.hasTo(ageRating)) {
            criteria.add(
                    cb.le(root.get("ageRating"), ageRating.toMap(Float::parseFloat))
            );
        }
        if (Range.hasFrom(releaseDate)) {
            criteria.add(
                    cb.greaterThanOrEqualTo(root.get("releaseDate"), releaseDate.<LocalDate>fromMap(LocalDate::parse))
            );
        }
        if (Range.hasTo(releaseDate)) {
            criteria.add(
                    cb.lessThanOrEqualTo(root.get("releaseDate"), releaseDate.<LocalDate>toMap(LocalDate::parse))
            );
        }
        if (CollectionUtils.isEmpty(criteria)) {
            return Specification.where((Specification<GameEntity>) null).toPredicate(root, query, cb);
        }
        return cb.and(criteria.toArray(Predicate[]::new));
    }
}
