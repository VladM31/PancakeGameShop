package nure.pancake.game.shop.gameproductservice.controllers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dto.LevelList;
import nure.pancake.game.shop.gameproductservice.filters.LevelFilter;
import nure.pancake.game.shop.gameproductservice.mappers.LevelListMapper;
import nure.pancake.game.shop.gameproductservice.services.LevelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/levels")
public class LevelController {
    private final LevelService levelService;
    private final LevelListMapper levelListMapper;

    @GetMapping
    public LevelList getLevels(LevelFilter filter){
        return levelListMapper.toLevelList(
                levelService.findBy(filter)
        );
    }
}
