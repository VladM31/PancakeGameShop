package nure.pancake.game.shop.authorizationservice.services;

import com.google.gson.Gson;
import lombok.*;
import nure.pancake.game.shop.authorizationservice.dataobjects.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Optional;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Builder
@RequiredArgsConstructor
public class JwtTokenService implements TokenService {
    private static final Logger LOG = LoggerFactory.getLogger(JwtTokenService.class);
    private final String secretWord;
    private final long plusExpirationTime;
    private final int maxRefreshCount;
    private final Gson gson;

    @Override
    public Token generate(@NonNull Token.User user) {
        return this.generate(toJwtUser(user));
    }

    @Override
    public Optional<Token> refresh(String token) {
        var user = parseJwtUser(token);
        if (user.filter(u -> u.getRefreshCount() < maxRefreshCount).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(generate(user.orElseThrow().incrementRefreshCount()));
    }

    @Override
    public Optional<Token.User> toUser(String token) {
        return parseJwtUser(token).map(u -> Token
                .User
                .builder()
                .phoneNumber(u.getPhoneNumber())
                .id(u.getId())
                .build());
    }

    private Token generate(JwtUser user) {
        var expirationTime = System.currentTimeMillis() + plusExpirationTime;
        var jwtToken = Jwts.builder().setSubject(gson.toJson(user))
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretWord).compact();

        return Token.builder().value(jwtToken).expirationTime(expirationTime).build();
    }

    private Optional<JwtUser> parseJwtUser(String token) {
        try {
            return Optional.of(gson.fromJson(Jwts.parser()
                    .setSigningKey(secretWord)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject(), JwtUser.class));
        } catch (IllegalArgumentException e) {
            LOG.warn(e.getMessage());
            return Optional.empty();
        }
    }

    private JwtUser toJwtUser(Token.User user) {
        return JwtUser.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class JwtUser {
        private Long id;
        private String phoneNumber;
        private int refreshCount;

        public JwtUser incrementRefreshCount() {
            ++this.refreshCount;
            return this;
        }
    }
}

