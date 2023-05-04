package nure.pancake.game.shop.gameproductservice.dto;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GenreList extends AbstractDtoList<String> {
    public GenreList(Page<String> pages) {
        super(pages);
    }
}
