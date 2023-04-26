package nure.pancake.game.shop.gameproductservice.services;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.convector.GenreSortFiledConvector;
import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.GenreSortFiled;
import nure.pancake.game.shop.gameproductservice.filters.GenreFilter;
import nure.pancake.game.shop.gameproductservice.repositories.GenreRepository;
import nure.pancake.game.shop.gameproductservice.search.criteria.GenreSearchCriteria;
import org.springframework.data.domain.Page;
import nure.pancake.game.shop.gameproductservice.entities.GenreEntity;

import java.util.List;

@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;
    private final GenreSortFiledConvector sortFiledConvector;

    @Override
    public Page<String> findBy(GenreFilter filter) {
        return repository.findAll(
                        GenreSearchCriteria.builder()
                                .name(filter.getName())
                                .build(),
                        filter.toPageable(0, 100, GenreSortFiled.NAME, sortFiledConvector::convert))
                .map(GenreEntity::getName);
    }

    @Override
    public String update(String oldGenre, String newGenre) {
        var genreOpt = repository.findOne(GenreSearchCriteria.builder()
                .names(List.of(oldGenre))
                .build());

        if (genreOpt.isEmpty()) {
            return null;
        }

        var genre = genreOpt.get();
        genre.setName(oldGenre);

        return repository.update(genre).getName();
    }

    @Override
    public String save(String genre) {
        return repository.save(new GenreEntity(null, genre)).getName();
    }
}
