package nure.pancake.game.shop.gameproductservice.dto;

import org.springframework.data.domain.Page;

public class GameLevelsList extends AbstractDtoList<GameLevelsRespond>{
    public GameLevelsList(Page<GameLevelsRespond> pages) {
        super(pages);
    }
}
