package co.jp.amazawa.training.demo.filter;

import co.jp.amazawa.training.demo.auth.JwtHelper;
import co.jp.amazawa.training.demo.auth.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final String[] skipAuthenticationPatterns = {
            "/login",
            "/refresh-token",
    };
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String servletPath = request.getServletPath();

        for (String pattern : skipAuthenticationPatterns) {
            if (pathMatcher.match(pattern, servletPath)) {
                // いずれかのパスにマッチした場合は認証をスキップ
                filterChain.doFilter(request, response);
                return;
            }
        }


        String accessToken = extractBearerToken(request);
        if (!StringUtils.hasText(accessToken)) {
            // Error
        }

        User user = jwtHelper.verifyAccessToken(accessToken);
        if (user == null) {
            // TODO Error
        }
        request.setAttribute("user", user);
        filterChain.doFilter(request, response);
    }

    private String extractBearerToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7); // "Bearer " の部分を除いたトークンを返す
        }
        return null;
    }

}
