package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.GameLevels;
import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import nure.pancake.game.shop.gameproductservice.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityGameLevelMapper {
    private final ModelMapper modelMapper;
    private final GenreRepository genreRepository;
    
    public EntityGameLevelMapper(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        this.modelMapper = new ModelMapper();
    }
    
    private void prepareMapper(){
        // todo
    }
    
    public GameEntity toGameEntity(GameLevels gameLevels){
        return null; // todo
    }

    public List<LevelEntity> toLevelEntityList(GameLevels gameLevels){
        return gameLevels.getLevels()
                .stream()
                .map(l -> modelMapper.map(l,LevelEntity.class))
                .collect(Collectors.toList());
    }
}
