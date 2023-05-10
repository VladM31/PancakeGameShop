package nure.pancake.game.shop.gameproductservice.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.function.Function;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Range<T> {
    private T from;
    private T to;

    @Nullable
    public <C> C toMap(Function<String,C> mapper){
        if(to == null){
            return null;
        }
        return mapper.apply(String.valueOf(to));
    }

    @Nullable
    public <C> C fromMap(Function<String,C> mapper){
        if(from == null){
            return null;
        }
        return mapper.apply(String.valueOf(from));
    }

    @Nullable
    public static <T> T from(@Nullable Range<T> range) {
        return range == null ? null : range.from;
    }

    public static boolean hasFrom(Range<?> range) {
        return range != null && range.from != null;
    }

    public static <T> Range<T> ofFrom(T from) {
        return new Range<T>(from, null);
    }

    @Nullable
    public static <T> T to(@Nullable Range<T> range) {
        return range == null ? null : range.to;
    }

    public static boolean hasTo(Range<?> range) {
        return range != null && range.to != null;
    }

    public static <T> Range<T> ofTo(T to) {
        return new Range<T>(null,to );
    }


}

