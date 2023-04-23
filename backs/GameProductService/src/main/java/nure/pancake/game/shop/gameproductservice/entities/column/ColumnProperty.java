package nure.pancake.game.shop.gameproductservice.entities.column;

import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ColumnProperty<COL, ENTITY> {
    private final Map<COL, String> properties;

    public ColumnProperty(
            @NonNull Class<ENTITY> clazz,
            @NonNull Map<COL, Function<ENTITY, ?>> entityProp) {
        this.properties = this.convert(clazz, entityProp);
    }

    private Map<COL, String> convert(Class<ENTITY> clazz, Map<COL, Function<ENTITY, ?>> entityProp) {
        Sort.TypedSort<ENTITY> typed = Sort.sort(clazz);

        HashMap<COL, String> props = new HashMap<>();

        entityProp.forEach((k, v) -> props.put(k, getColumn(typed, v)));

        return Collections.unmodifiableMap(props);
    }

    private String getColumn(Sort.TypedSort<ENTITY> typed, Function<ENTITY, ?> prop) {
        var iter = typed.by(prop).iterator();
        if (iter.hasNext()) {
            return iter.next().getProperty();
        }
        throw new RuntimeException("Column not found");
    }

    @Nullable
    public String getProp(COL column){
        if(column == null){
            return null;
        }
        return properties.get(column);
    }

    public Collection<String> getProps(){
        return properties.values();
    }
}
