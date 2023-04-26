package nure.pancake.game.shop.gameproductservice.filters;


import lombok.*;
import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.GenreSortFiled;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GenreFilter extends FilterPage<GenreSortFiled> {
    private Collection<String> names;
    private String name;
}
