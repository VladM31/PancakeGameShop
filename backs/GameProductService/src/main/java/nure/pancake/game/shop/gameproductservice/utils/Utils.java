package nure.pancake.game.shop.gameproductservice.utils;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Utils {
    public static <T> T ifNullElse(T obj, T defObj) {
        if (Objects.isNull(obj) && Objects.isNull(defObj)) {
            return null;
        }
        return Objects.requireNonNullElse(obj, defObj);
    }

    public static <T, R> R ifNullElse(Function<T, R> toReturnObj, T fromObj, T fromDefObj) {
        return Utils.ifNullElse(toReturnObj.apply(fromObj), toReturnObj.apply(fromDefObj));
    }

    public static <T, R> R ifNotFilterElse(Function<T, R> toReturnObj, Predicate<R> predicate, T fromObj, T fromDefObj) {
        R result = toReturnObj.apply(fromObj);
        if (predicate.test(result)) {
            return result;
        }
        return toReturnObj.apply(fromDefObj);
    }
}
