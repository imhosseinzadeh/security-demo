package ir.maktab.securitydemo.authentication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class EmailPasswordAuthenticationConverter implements AuthenticationConverter {
    private static final TypeReference<Map<String, String>> MAP_TYPE_REFERENCE = new TypeReference<>() {
    };

    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Authentication convert(HttpServletRequest request) {

        try {
            String requestBody = request.getInputStream().toString();

            Map<String, String> map = mapper.readValue(requestBody, MAP_TYPE_REFERENCE);

            String email = map.get(EMAIL_KEY);
            String password = map.get(PASSWORD_KEY);

            return EmailPasswordAuthenticationToken.unauthenticated(email, password);

        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }


}
