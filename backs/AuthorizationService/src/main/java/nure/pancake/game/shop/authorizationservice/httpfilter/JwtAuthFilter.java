package nure.pancake.game.shop.authorizationservice.httpfilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.authorizationservice.services.TokenService;
import nure.pancake.game.shop.authorizationservice.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;

@Builder
@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {
    private static final String HEADER_STRING = "Authorization";
    private final TokenService tokenService;
    private final UserService userService;
    private final String tokenPrefix;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            if ("OPTIONS".equals(request.getMethod())) {
                return;
            }
            String token = getToken(request);
            if (token == null) {
                return;
            }
            var tokenUser = tokenService.toUser(token);
            if (tokenUser.isEmpty()) {
                return;
            }

            SecurityContextHolder.getContext().setAuthentication(buildAuthentication(
                    tokenUser.orElseThrow().getId()
            ));
        } finally {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (!StringUtils.hasText(token)) {
            return null;
        }
        return token.replaceFirst(tokenPrefix, "").trim();
    }

    private Authentication buildAuthentication(Long userId) {
        var user = userService.findById(userId).orElseThrow();

        return new UsernamePasswordAuthenticationToken(
                user,
                null,
                List.of(user.getRole()));
    }
}
