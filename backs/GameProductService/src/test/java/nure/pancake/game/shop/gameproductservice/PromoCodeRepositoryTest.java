package nure.pancake.game.shop.gameproductservice;

import nure.pancake.game.shop.gameproductservice.entities.PromoCodeEntity;
import nure.pancake.game.shop.gameproductservice.repositories.PromoCodeRepository;
import nure.pancake.game.shop.gameproductservice.search.criteria.PromoCodeSearchCriteria;
import nure.pancake.game.shop.gameproductservice.utils.Range;
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
public class PromoCodeRepositoryTest {
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    private PromoCodeEntity getPromoCode() {
        return promoCodeRepository.findAll(PromoCodeSearchCriteria.builder().build(), PageRequest.of(1, 1))
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Test
    public void testFindByPromoCodeId() {
        var promoId = getPromoCode().getId();

        var promo = promoCodeRepository.findOne(
                        PromoCodeSearchCriteria.builder().promoId(promoId).build())
                .orElseThrow();

        Assertions.assertThat(promo.getId())
                .isEqualTo(promoId);
    }

    @Test
    public void testFindByDiscountPercentageMore() {
        var discountPercentage = getPromoCode().getDiscountPercentage();
        var promo = promoCodeRepository.findAll(
                PromoCodeSearchCriteria.builder().discountPercentage(Range.ofFrom(discountPercentage)).build());
        Assertions.assertThat(promo)
                .allMatch(p -> p.getDiscountPercentage() >= discountPercentage);
    }

    @Test
    public void testFindByDiscountPercentageLess() {
        var discountPercentage = getPromoCode().getDiscountPercentage();
        var promo = promoCodeRepository.findAll(
                PromoCodeSearchCriteria.builder().discountPercentage(Range.ofTo(discountPercentage)).build());
        Assertions.assertThat(promo)
                .allMatch(p -> p.getDiscountPercentage() <= discountPercentage);
    }

    @Test
    public void testFindByEndDateMore() {
        var endDate = getPromoCode().getEndDate();

        var promo = promoCodeRepository.findAll(
                PromoCodeSearchCriteria.builder().endDate(Range.ofFrom(endDate)).build());

        Assertions.assertThat(promo).allMatch(p -> p.getEndDate().isAfter(endDate) || p.getEndDate().equals(endDate));
    }

    @Test
    public void testFindByEndDateLess() {
        var endDate = getPromoCode().getEndDate();

        var promo = promoCodeRepository.findAll(
                PromoCodeSearchCriteria.builder().endDate(Range.ofTo(endDate)).build());

        Assertions.assertThat(promo).allMatch(p -> p.getEndDate().isBefore(endDate) || p.getEndDate().equals(endDate));
    }

    @After
    public void showSuccess() {
        System.out.println("Success");
    }
}