package nure.pancake.game.shop.authorizationservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String value;
    private long expirationTime;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private Long id;
        private String phoneNumber;
    }
}