package nure.pancake.game.shop.gameproductservice.controllers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dto.GenreList;
import nure.pancake.game.shop.gameproductservice.filters.GenreFilter;
import nure.pancake.game.shop.gameproductservice.mappers.GenreListMapper;
import nure.pancake.game.shop.gameproductservice.services.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;
    private final GenreListMapper genreListMapper;

    @GetMapping
    public GenreList getGenres(GenreFilter filter) {
        return genreListMapper.toGenreList(
                genreService.findBy(filter)
        );
    }
}
