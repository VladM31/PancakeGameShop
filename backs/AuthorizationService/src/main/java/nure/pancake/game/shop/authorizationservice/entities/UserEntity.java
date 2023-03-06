package nure.pancake.game.shop.authorizationservice.entities;

import nure.pancake.game.shop.authorizationservice.dataobjects.UserState;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

public class UserEntity {
    private Long id;
    private String phoneNumber;
    private String password;
    private String n;
    private String f;
    private String l;
    private LocalDate birthDate;
    private boolean active;
    private String selectedCurrency;
    private UserState userState;
    @Nullable
    private String email;
    private RoleEntity role;
}
