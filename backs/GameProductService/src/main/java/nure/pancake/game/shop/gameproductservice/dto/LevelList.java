package nure.pancake.game.shop.gameproductservice.dto;

import org.springframework.data.domain.Page;

public class LevelList extends AbstractDtoList<LevelRespond>{
    public LevelList(Page<LevelRespond> pages) {
        super(pages);
    }
}
