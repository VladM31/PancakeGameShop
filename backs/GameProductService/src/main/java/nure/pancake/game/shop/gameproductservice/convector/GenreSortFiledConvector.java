package nure.pancake.game.shop.gameproductservice.convector;

import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.GenreSortFiled;
import nure.pancake.game.shop.gameproductservice.entities.column.GenreColumn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class GenreSortFiledConvector {
    private final Map<GenreSortFiled, String> mapper = build();

    public String convert(@NonNull GenreSortFiled filed) {
        return mapper.get(filed);
    }

    private Map<GenreSortFiled, String> build() {
        return Map.of(
                GenreSortFiled.ID, Objects.requireNonNull(GenreColumn.ID.gfn()),
                GenreSortFiled.NAME, Objects.requireNonNull(GenreColumn.NAME.gfn())
        );
    }
}
