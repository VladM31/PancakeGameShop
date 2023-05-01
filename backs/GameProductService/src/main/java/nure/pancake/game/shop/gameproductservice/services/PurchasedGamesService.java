package nure.pancake.game.shop.gameproductservice.services;

import nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGame;
import nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGameDetails;
import nure.pancake.game.shop.gameproductservice.filters.PurchasedGameFilter;
import org.springframework.data.domain.Page;

public interface PurchasedGamesService {
    Page<PurchasedGame> findBy(PurchasedGameFilter filter);
    Page<PurchasedGameDetails> findDetailsBy(PurchasedGameFilter filter);
}
