package nure.pancake.game.shop.gameproductservice.services;

import jakarta.validation.ValidationException;
import nure.pancake.game.shop.gameproductservice.dataobjects.PromoCode;
import nure.pancake.game.shop.gameproductservice.entities.PromoCodeEntity;
import nure.pancake.game.shop.gameproductservice.exceptions.PromoCodeException;
import nure.pancake.game.shop.gameproductservice.filters.PromoCodeFilter;
import nure.pancake.game.shop.gameproductservice.mappers.PromoCodeFilterMapper;
import nure.pancake.game.shop.gameproductservice.mappers.PromoCodeMapper;
import nure.pancake.game.shop.gameproductservice.repositories.PromoCodeRepository;
import nure.pancake.game.shop.gameproductservice.search.criteria.PromoCodeSearchCriteria;
import nure.pancake.game.shop.gameproductservice.utils.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;



public class PromoCodeServiceImpl implements PromoCodeService{
    private static final Logger LOG = LoggerFactory.getLogger(PromoCodeServiceImpl.class);
    private final PromoCodeMapper mapper;
    private final PromoCodeRepository repository;
    private final PromoCodeFilterMapper filterMapper;
    private final Cipher encoder;
    private final Cipher decoder;


    public PromoCodeServiceImpl(PromoCodeMapper mapper, PromoCodeRepository repository, String key, PromoCodeFilterMapper filterMapper)  {
        this.mapper = mapper;
        this.repository = repository;
        this.filterMapper = filterMapper;

        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        try{
            this.encoder = Cipher.getInstance("AES");
            encoder.init(Cipher.ENCRYPT_MODE, secretKey);
            this.decoder = Cipher.getInstance("AES");
            decoder.init(Cipher.DECRYPT_MODE, secretKey);
        }catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e){
            throw new PromoCodeException(e.getMessage(),e);
        }

    }

    @Override
    public int getDiscountPercentage(String code) {
        if(!StringUtils.hasText(code) || code.length() < 10 || code.length() > 35){
            throw new ValidationException("Code is blank or length is not between 10 and 35");
        }
        try {
            Long id = decode(code);

            return repository.findOne(
                    PromoCodeSearchCriteria
                            .builder()
                            .promoId(id)
                            .endDate(Range.ofFrom(LocalDate.now()))
                            .build()
            ).map(PromoCodeEntity::getDiscountPercentage).orElse(0);
        }catch (PromoCodeException e){
            LOG.warn(e.getMessage(),e);
            return 0;
        }
    }

    @Override
    public List<PromoCode> findAll(PromoCodeFilter filter) {
        return repository.findAll(filterMapper.toPromoCodeSearchCriteria(filter))
                .stream()
                .map(p -> mapper.toPromoCode(p,encode(p.getId())))
                .toList();
    }

    @Override
    public PromoCode save(PromoCode code) {
        var result = repository.save(mapper.toPromoCodeEntity(code));

        return mapper.toPromoCode(
                result,
                encode(result.getId())
        );
    }

    @Override
    public PromoCode update(PromoCode code) {
        return mapper.toPromoCode(
                repository.update(mapper.toPromoCodeEntity(code)),
                encode(code.id())
        );
    }

    private String encode(Long id){
        try {
            byte[] encrypted  = encoder.doFinal(id.toString().getBytes());
            String code = Base64.getEncoder().encodeToString(encrypted);
            return code.substring(0,code.length()-2);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new PromoCodeException(e.getMessage(),e);
        }
    }

    private Long decode(String code){
        try {
            byte[] decoded = Base64.getDecoder().decode(code + "==");
            byte[] decrypted = decoder.doFinal(decoded);
            return Long.valueOf(new String(decrypted));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new PromoCodeException(e.getMessage(),e);
        }
    }
}
