package nure.pancake.game.shop.authorizationservice.dataobjects;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMINISTRATION, CUSTOMER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
