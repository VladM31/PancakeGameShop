package nure.pancake.game.shop.gameproductservice.search.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import nure.pancake.game.shop.gameproductservice.entity.Game;
import nure.pancake.game.shop.gameproductservice.entity.Genre;
import nure.pancake.game.shop.gameproductservice.utils.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameSearchCriteria implements Specification<Game> {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> gameIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> genreIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> genreNames;
    private Range<Float> price;
    private Range<Float> ageRating;
    private Range<Float> releaseDate;
    private String name;

    @Override
    public Predicate toPredicate(Root<Game> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> criteria = new ArrayList<>();

        if (!CollectionUtils.isEmpty(gameIds)) {
            criteria.add(cb.in(root.
                    get("id")).value(gameIds));
        }
        if (!CollectionUtils.isEmpty(genreIds)) {
            criteria.add(cb.in(root
                            .get(Game.FieldName.GENRE.getFieldName())
                            .get(Genre.FieldName.GENRE_ID.getFieldName()))
                    .value(genreIds));
        }
        if (!CollectionUtils.isEmpty(genreNames)) {
            criteria.add(cb.in(root
                            .get(Game.FieldName.GENRE.getFieldName())
                            .get(Genre.FieldName.GENRE_NAME.getFieldName()))
                    .value(genreNames));
        }
        if (StringUtils.hasText(name)) {
            criteria.add(cb.like(root.get("name"), "%" + name + "%"));
        }
        if (Range.hasFrom(price)) {
            criteria.add(
                    cb.ge(root.get("price"), price.getFrom())
            );
        }
        if (Range.hasTo(price)) {
            criteria.add(
                    cb.le(root.get("price"), price.getTo())
            );
        }
        if (Range.hasFrom(ageRating)) {
            criteria.add(
                    cb.ge(root.get("ageRating"), ageRating.getFrom())
            );
        }
        if (Range.hasTo(ageRating)) {
            criteria.add(
                    cb.le(root.get("ageRating"), ageRating.getTo())
            );
        }
        if (Range.hasFrom(releaseDate)) {
            criteria.add(
                    cb.ge(root.get("releaseDate"), releaseDate.getFrom())
            );
        }
        if (Range.hasTo(releaseDate)) {
            criteria.add(
                    cb.le(root.get("releaseDate"), releaseDate.getTo())
            );
        }
        if (CollectionUtils.isEmpty(criteria)) {
            return Specification.where((Specification<Game>) null).toPredicate(root, query, cb);
        }
        return cb.and(criteria.toArray(Predicate[]::new));
    }
}
