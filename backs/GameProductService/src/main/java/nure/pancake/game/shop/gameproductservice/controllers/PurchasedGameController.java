package nure.pancake.game.shop.gameproductservice.controllers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dataobjects.Role;
import nure.pancake.game.shop.gameproductservice.dataobjects.User;
import nure.pancake.game.shop.gameproductservice.dto.PurchasedGameDetailsList;
import nure.pancake.game.shop.gameproductservice.dto.PurchasedGameIdsList;
import nure.pancake.game.shop.gameproductservice.dto.PurchasedGameList;
import nure.pancake.game.shop.gameproductservice.filters.PurchasedGameFilter;
import nure.pancake.game.shop.gameproductservice.mappers.PurchasedGameDetailsListMapper;
import nure.pancake.game.shop.gameproductservice.mappers.PurchasedGameIdsListMapper;
import nure.pancake.game.shop.gameproductservice.mappers.PurchasedGameListMapper;
import nure.pancake.game.shop.gameproductservice.services.PurchasedGamesService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/bought-content")
public class PurchasedGameController {
    private final PurchasedGameListMapper purGameListMapper;
    private final PurchasedGameIdsListMapper purGameIdsListMapper;
    private final PurchasedGameDetailsListMapper purGameDetailsListMapper;
    private final PurchasedGamesService purGamesService;

    @ModelAttribute
    private void modifyFilter(@AuthenticationPrincipal User user, PurchasedGameFilter filter){
        if(!Role.ADMINISTRATION.equals(user.getRole())){
            filter.setUserIds(List.of(user.getId()));
        }
    }

    @GetMapping
    public PurchasedGameList get(PurchasedGameFilter filter){
        return purGameListMapper.toPurchasedGameList(
                purGamesService.findBy(filter)
        );
    }

    @GetMapping("/ids")
    public PurchasedGameIdsList getIds(PurchasedGameFilter filter){
        return purGameIdsListMapper.toPurchasedGameIdsList(
                purGamesService.findBy(filter)
        );
    }

    @GetMapping("/details")
    public PurchasedGameDetailsList getDetails(PurchasedGameFilter filter){
        return purGameDetailsListMapper.toPurchasedGameDetailsList(
                purGamesService.findDetailsBy(filter)
        );
    }
}
