package nure.pancake.game.shop.gameproductservice.mappers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dataobjects.Level;
import nure.pancake.game.shop.gameproductservice.dto.LevelList;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LevelListMapper {
    private final LevelRespondMapper mapper;

    public LevelList toLevelList(Page<Level> pages){
        return new LevelList(
                pages.map(mapper::toLevelRespond)
        );
    }
}
