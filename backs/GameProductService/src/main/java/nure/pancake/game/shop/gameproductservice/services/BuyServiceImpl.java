package nure.pancake.game.shop.gameproductservice.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.clients.BuyClient;
import nure.pancake.game.shop.gameproductservice.dataobjects.BuyContent;
import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedGameEntity;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedLevelEntity;
import nure.pancake.game.shop.gameproductservice.exceptions.BuyContentException;
import nure.pancake.game.shop.gameproductservice.repositories.GameRepository;
import nure.pancake.game.shop.gameproductservice.repositories.LevelRepository;
import nure.pancake.game.shop.gameproductservice.repositories.PurchasedGameRepository;
import nure.pancake.game.shop.gameproductservice.repositories.PurchasedLevelRepository;
import nure.pancake.game.shop.gameproductservice.search.criteria.GameSearchCriteria;
import nure.pancake.game.shop.gameproductservice.search.criteria.LevelSearchCriteria;
import nure.pancake.game.shop.gameproductservice.search.criteria.PurchasedGameSearchCriteria;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@RequiredArgsConstructor
public class BuyServiceImpl implements BuyService {
    private final PurchasedGameRepository purchasedGameRepository;
    private final PurchasedLevelRepository purchasedLevelRepository;
    private final GameRepository gameRepository;
    private final LevelRepository levelRepository;
    private final BuyClient buyClient;
    private final PromoCodeService promoCodeService;

    @Transactional
    public boolean buy(BuyContent buyContent) {
        var boughtStore = getBoughtContentStore(buyContent.getUserId());

        if (boughtStore.isBought(buyContent)) {
            throw new BuyContentException("Content is bought");
        }

        var existStore = getExistContentStore(buyContent);

        if (!existStore.exists(buyContent)) {
            throw new BuyContentException("Content is not exists");
        }

        if (!canBuyLevel(existStore, boughtStore)) {
            throw new BuyContentException("Can but level because game not buy");
        }

        var discount = Optional.ofNullable(buyContent.getPromoCode()).map( c -> c.isBlank() ? null : c)
                .map(promoCodeService::getDiscountPercentage).orElse(0);

        if (!buyClient.sentTransaction(buyContent, getPrice(existStore,discount))) {
            throw new BuyContentException("Payment error");
        }

        Map<Long, PurchasedGameEntity> purByGameId = new HashMap<>(boughtStore.purByGameId);

        var dateNow = LocalDateTime.now();

        buyContent.getGameIds().forEach(gameId ->
            purByGameId.put(gameId, purchasedGameRepository.save(
                    new PurchasedGameEntity(null, dateNow, buyContent.getUserId(), gameId, null)))
        );

        buyContent.getLevelIds().forEach( levelId ->
            purchasedLevelRepository.save(
                    new PurchasedLevelEntity(null,dateNow,levelId, getPurchasedGameId(levelId, existStore.getLevelStore(),purByGameId))
            )
        );

        return true;
    }

    public BoughtContentStore getBoughtContentStore(Long userId) {
        Map<Long, PurchasedGameEntity> purByGameId = this.purchasedGameRepository.findAll(
                        PurchasedGameSearchCriteria.builder().userId(userId).build()
                )
                .stream()
                .collect(Collectors.toMap(PurchasedGameEntity::getGamesId, p -> p));

        List<Long> boughtLevelIds = purByGameId
                .values()
                .stream()
                .flatMap(p -> p.getLevels()
                        .stream()
                        .map(PurchasedLevelEntity::getLevelsId))
                .toList();

        return new BoughtContentStore(purByGameId.keySet(), boughtLevelIds, purByGameId);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class BoughtContentStore {
        private Collection<Long> boughtGameIds;
        private Collection<Long> boughtLevelIds;
        private Map<Long, PurchasedGameEntity> purByGameId;

        public boolean isBought(BuyContent buyContent) {
            return CollectionUtils.containsAny(boughtGameIds, buyContent.getGameIds()) ||
                    CollectionUtils.containsAny(boughtLevelIds, buyContent.getLevelIds());
        }
    }

    private ExistContentStore getExistContentStore(BuyContent buyContent) {
        Map<Long, GameEntity> gameStore = buyContent.getGameIds().isEmpty()? Map.of() : gameRepository.findAll(
                        GameSearchCriteria.builder().gameIds(buyContent.getGameIds()).build())
                .stream().collect(Collectors.toMap(GameEntity::getId, g -> g));

        Map<Long, LevelEntity> levelStore = buyContent.getLevelIds().isEmpty() ? Map.of() :levelRepository.findAll(
                        LevelSearchCriteria.builder().levelIds(buyContent.getLevelIds()).build())
                .stream().collect(Collectors.toMap(LevelEntity::getId, l -> l));

        return new ExistContentStore(gameStore, levelStore);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ExistContentStore {
        private Map<Long, GameEntity> gameStore;
        private Map<Long, LevelEntity> levelStore;

        public boolean exists(BuyContent buyContent) {
            int difCount = 0;

            if (!buyContent.getGameIds().isEmpty()) {
                difCount = gameStore.keySet().size() == buyContent.getGameIds().size() ? difCount : difCount + 1;
            }
            if(!buyContent.getLevelIds().isEmpty()){
                difCount = levelStore.keySet().size() == buyContent.getLevelIds().size() ? difCount : difCount + 1;
            }

            return difCount == 0;
        }
    }

    private boolean canBuyLevel(ExistContentStore existContentStore, BoughtContentStore boughtContentStore) {
        return existContentStore
                .levelStore
                .values()
                .stream()
                .map(LevelEntity::getGameId)
                .allMatch(gId -> existContentStore.getGameStore().containsKey(gId) || boughtContentStore.boughtGameIds.contains(gId));
    }

    private Float getPrice(ExistContentStore existContentStore,int discount) {
        var price = DoubleStream.concat(
                existContentStore.gameStore.values().stream().mapToDouble(GameEntity::getPrice),
                existContentStore.levelStore.values().stream().mapToDouble(LevelEntity::getPrice)
        ).sum();



        return (float)(price - (price / 100.0 * discount));
    }

    private Long getPurchasedGameId(Long levelId,Map<Long, LevelEntity> levelStore,Map<Long, PurchasedGameEntity> purByGameId){

        return purByGameId.get(levelStore.get(levelId).getGameId()).getId();
    }
}
