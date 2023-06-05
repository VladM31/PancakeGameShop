package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.DownloadGame;
import nure.pancake.game.shop.gameproductservice.dataobjects.Platforms;
import nure.pancake.game.shop.gameproductservice.dto.DownloadGameRequest;
import nure.pancake.game.shop.gameproductservice.exceptions.PlatformNotExists;
import org.springframework.stereotype.Component;

@Component
public class DownloadGameMapper {

    public DownloadGame toDownloadGame(DownloadGameRequest request, Long userId) throws PlatformNotExists{
        Platforms platform;
        try{
            platform = Platforms.valueOf(request.getPlatform());
        }catch (IllegalArgumentException | NullPointerException e){
            throw new PlatformNotExists("Платформи не існує");
        }

        return new DownloadGame(
                userId,
                request.getGameId(),
                platform
        );
    }
}
