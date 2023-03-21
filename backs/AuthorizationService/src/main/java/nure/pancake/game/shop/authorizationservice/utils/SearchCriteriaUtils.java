package nure.pancake.game.shop.authorizationservice.utils;

import jakarta.persistence.criteria.*;
import nure.pancake.game.shop.authorizationservice.entities.FieldNameGettable;

import java.util.stream.Stream;

public class SearchCriteriaUtils {
    public static <T> Predicate contains(Root<T> root, CriteriaBuilder cb, String value, String... fieldNames) {
        return cb.like((Expression<String>) getPath(root, fieldNames), "%" + value + "%");
    }

    public static <T> Predicate contains(Root<T> root, CriteriaBuilder cb, String value, FieldNameGettable... fieldName) {
        return SearchCriteriaUtils.contains(root, cb, value,
                Stream
                        .of(fieldName)
                        .map(FieldNameGettable::getFieldName)
                        .toArray(String[]::new));
    }


    private static <T> Path<T> getPath(Root<T> root, String[] fieldNames) {
        if (fieldNames == null || fieldNames.length == 0) {
            throw new RuntimeException("Field names is empty");
        }

        Path<T> field = null;
        for (String name : fieldNames) {
            field = root.get(name);
        }
        return field;
    }
}
