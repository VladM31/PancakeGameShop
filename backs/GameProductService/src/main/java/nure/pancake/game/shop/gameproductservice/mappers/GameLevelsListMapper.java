package nure.pancake.game.shop.gameproductservice.mappers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dataobjects.GameLevels;
import nure.pancake.game.shop.gameproductservice.dto.GameLevelsList;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameLevelsListMapper {
    private final GameLevelsRespondMapper mapper;

    public GameLevelsList toGameLevelsList(Page<GameLevels> pages){
        return new GameLevelsList(
                pages.map(mapper::toGameLevelsRespond)
        );
    }
}
