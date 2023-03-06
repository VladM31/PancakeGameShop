package nure.pancake.game.shop.authorizationservice.entities;

import java.time.LocalDateTime;

public class AuthEntity {
    private Long id;
    private String value;
    private LocalDateTime dateOfCreation;
    private boolean active;
    private Long userId;
}
