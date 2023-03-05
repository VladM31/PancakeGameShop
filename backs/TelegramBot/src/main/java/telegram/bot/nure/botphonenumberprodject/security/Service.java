package telegram.bot.nure.botphonenumberprodject.security;

import org.springframework.security.core.GrantedAuthority;

public enum Service implements GrantedAuthority {
    BACKEND, FRONT, GAME;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
