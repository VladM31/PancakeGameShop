package nure.pancake.game.shop.gameproductservice.dto;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;


@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GameList extends AbstractDtoList<GameRespond>{
    public GameList(Page<GameRespond> pages) {
        super(pages);
    }
}
