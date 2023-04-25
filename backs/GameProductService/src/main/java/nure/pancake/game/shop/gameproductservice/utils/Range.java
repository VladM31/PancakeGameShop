package nure.pancake.game.shop.gameproductservice.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Range<T> {
    private T to;
    private T from;

    @Nullable
    public static <T> T from(@Nullable Range<T> range) {
        return range == null ? null : range.from;
    }

    public static boolean hasFrom(Range<?> range) {
        return range != null && range.from != null;
    }

    public static <T> Range<T> ofFrom(T from) {
        return new Range<T>(null, from);
    }

    @Nullable
    public static <T> T to(@Nullable Range<T> range) {
        return range == null ? null : range.to;
    }

    public static boolean hasTo(Range<?> range) {
        return range != null && range.to != null;
    }

    public static <T> Range<T> ofTo(T to) {
        return new Range<T>(to, null);
    }


}
