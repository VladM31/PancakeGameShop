package nure.pancake.game.shop.gameproductservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.services.AuthService;
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
    private final String tokenPrefix;
    private final AuthService authService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            if ("OPTIONS".equals(request.getMethod())) {
                return;
            }

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(getAuthentication(request));
        } finally {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (!StringUtils.hasText(token)) {
            return null;
        }
        token = token.replaceFirst(tokenPrefix, "").trim();

        var user = authService.getUser(token);

        if (user.isEmpty()) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(
                user.get(),
                null,
                List.of(user.get().getRole()));
    }
}
