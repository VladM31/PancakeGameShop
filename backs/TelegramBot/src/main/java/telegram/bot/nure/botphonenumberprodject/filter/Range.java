package telegram.bot.nure.botphonenumberprodject.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Range<T> {
    private T to;
    private T from;

    @Nullable
    public static <T> T to(Range<T> range){
        return range == null ? null : range.to;
    }

    @Nullable
    public static <T> T from(Range<T> range){
        return range == null ? null : range.from;
    }
}
