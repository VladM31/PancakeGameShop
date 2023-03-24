package nure.pancake.game.shop.authorizationservice.configs;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean("constantLifetimeCacheManager")
    public CacheManager constantLifetimeCacheManager() {
        var cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(constantLifetimeCacheBuilder());
        return cacheManager;
    }

    private Caffeine<Object, Object> constantLifetimeCacheBuilder() {
        return Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .expireAfterAccess(15, TimeUnit.MINUTES);
    }
}
