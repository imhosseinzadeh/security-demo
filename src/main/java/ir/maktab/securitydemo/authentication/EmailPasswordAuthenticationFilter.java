package ir.maktab.securitydemo.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmailPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("api/users/login", "POST");
    private final EmailPasswordAuthenticationConverter authenticationConverter = new EmailPasswordAuthenticationConverter();

    protected EmailPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {

        Authentication authentication = authenticationConverter.convert(request);
        if (authentication == null) {
            return null;
        }

        return super.getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {


        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", "");
        responseBody.put("refreshToken", "");
        responseBody.put("expiration", "");
    }
}
