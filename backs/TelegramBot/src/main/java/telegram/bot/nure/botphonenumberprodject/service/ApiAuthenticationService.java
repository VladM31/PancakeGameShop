package telegram.bot.nure.botphonenumberprodject.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface ApiAuthenticationService {
    Authentication getAuthentication(HttpServletRequest request);
}
