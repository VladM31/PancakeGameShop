package nure.pancake.game.shop.gameproductservice.entities;

public interface FieldNameGettable {
    String getFieldName();

    default String gfn(){
        return getFieldName();
    }
}
