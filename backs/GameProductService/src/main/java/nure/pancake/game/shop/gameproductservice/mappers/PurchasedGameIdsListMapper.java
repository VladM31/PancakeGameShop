package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGame;
import nure.pancake.game.shop.gameproductservice.dto.PurchasedGameIdsList;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PurchasedGameIdsListMapper {

    public PurchasedGameIdsList toPurchasedGameIdsList(Page<PurchasedGame> pages){
        return new PurchasedGameIdsList(pages.map(this::toPurGameIds));
    }

    private PurchasedGameIdsList.PurGameIds toPurGameIds(PurchasedGame purchasedGame){
        return new PurchasedGameIdsList.PurGameIds(
                purchasedGame.getGamesId(),
                purchasedGame.getLevels().stream().map(PurchasedGame.PGLevel::getLevelsId).toList()
        );
    }
}
