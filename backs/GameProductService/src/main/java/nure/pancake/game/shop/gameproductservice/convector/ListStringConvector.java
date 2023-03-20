package nure.pancake.game.shop.gameproductservice.convector;


import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;
import java.util.List;

public class ListStringConvector implements AttributeConverter<List<String>, String> {
private final Gson gson = new Gson();
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return gson.toJson(strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return gson.fromJson(s, List.class);
    }
}
