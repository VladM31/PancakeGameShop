package telegram.bot.nure.botphonenumberprodject.service;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import telegram.bot.nure.botphonenumberprodject.security.AuthenticationUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TokenAuthenticationService implements ApiAuthenticationService {
    private final static String TOKEN_PREFIX = "Bearer";
    private final static String HEADER_STRING = "Authorization";

    private final String secret;
    private final Gson gson;

    public TokenAuthenticationService(String secret) {
        this.secret = secret;
        this.gson = new Gson();
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // parse the token.
            String userJson = Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(removeTokenPrefix(token))
                    .getBody()
                    .getSubject();
            // parse the json.
            AuthenticationUser authUser = gson.fromJson(userJson, AuthenticationUser.class);

            return new UsernamePasswordAuthenticationToken(
                    authUser,
                    null,
                    List.of(authUser.getService()));

        }
        return null;
    }

    private String removeTokenPrefix(String token) {
        return token.replace(TOKEN_PREFIX, "");
    }
}
