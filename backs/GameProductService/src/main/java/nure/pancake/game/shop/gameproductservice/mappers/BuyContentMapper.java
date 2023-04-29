package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.BuyContent;
import nure.pancake.game.shop.gameproductservice.dto.BuyContentRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BuyContentMapper {
    private final ModelMapper mapper = new ModelMapper();

    public BuyContent toBuyContent(BuyContentRequest request, Long userId) {
        var buyContent = mapper.map(request, BuyContent.class);
        buyContent.setUserId(userId);
        buyContent.setGameIds(buyContent.getGameIds() == null ? Set.of() : buyContent.getGameIds());
        buyContent.setLevelIds(buyContent.getLevelIds() == null ? Set.of() : buyContent.getLevelIds());
        return buyContent;
    }
}
