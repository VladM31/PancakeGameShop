package nure.pancake.game.shop.gameproductservice.clients;

import nure.pancake.game.shop.gameproductservice.dataobjects.BuyContent;

import java.util.Random;

public class PlugBuyClient implements BuyClient{
    private final Random random = new Random();

    @Override
    public boolean sentTransaction(BuyContent content,Float price) {
        return random.nextInt(0,100) < 90;
    }
}
