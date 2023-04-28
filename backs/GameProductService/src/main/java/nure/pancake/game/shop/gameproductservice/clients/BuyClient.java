package nure.pancake.game.shop.gameproductservice.clients;

import nure.pancake.game.shop.gameproductservice.dataobjects.BuyContent;

public interface BuyClient {
    boolean sentTransaction(BuyContent content,Float price);
}
