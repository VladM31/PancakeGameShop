package nure.pancake.game.shop.gameproductservice;

import nure.pancake.game.shop.gameproductservice.entities.GenreEntity;
import nure.pancake.game.shop.gameproductservice.repositories.GenreRepository;
import nure.pancake.game.shop.gameproductservice.search.criteria.GenreSearchCriteria;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    private GenreEntity getGenre() {
        return genreRepository.findAll(GenreSearchCriteria.builder().build(), PageRequest.of(1, 1))
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Test
    public void testFindByGenreId() {
        var genreId = getGenre().getId();

        var genre = genreRepository.findOne(
                        GenreSearchCriteria.builder().genreId(genreId).build())
                .orElseThrow();

        Assertions.assertThat(genre.getId())
                .isEqualTo(genreId);
    }

    @Test
    public void testFindByName() {
        var name = getGenre().getName().substring(0, 2);
        var genres = genreRepository.findAll(
                GenreSearchCriteria.builder().name(name).build());
        Assertions.assertThat(genres)
                .allMatch(g -> g.getName().contains(name));
    }

    @Test
    public void testFindByNames() {
        var names = getGenre().getName();
        var genres = genreRepository.findAll(
                GenreSearchCriteria.builder().name(names).build());
        Assertions.assertThat(genres)
                .allMatch(g -> g.getName().contains(names));
    }

    @After
    public void showSuccess() {
        System.out.println("Success");
    }
}
