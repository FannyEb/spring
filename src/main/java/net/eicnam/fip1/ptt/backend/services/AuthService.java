package net.eicnam.fip1.ptt.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.eicnam.fip1.ptt.backend.controllers.AuthController;
import net.eicnam.fip1.ptt.backend.models.RUser;
import net.eicnam.fip1.ptt.backend.utils.JWTDecode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import net.eicnam.fip1.ptt.backend.utils.DotEnvReader;
import net.eicnam.fip1.sdk.rainbow.RainbowApplication;
import net.eicnam.fip1.ptt.backend.repositories.UserRepository;
import net.eicnam.fip1.sdk.rainbow.authentication.RainbowAuthentication;


@AllArgsConstructor
@Service
public class AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);
    private final UserRepository userRepository;

    public String login(final String email, final String password) {

        final RainbowApplication rainbowApplication = RainbowApplication.getInstance();
        final DotEnvReader env = rainbowApplication.getEnvReader();

        final String token = new RainbowAuthentication(
                env.getValue("API_URL"),
                email,
                password,
                env.getValue("API_APP_ID"),
                env.getValue("API_APP_SECRET")
        ).getToken();

        final String payload = JWTDecode.decode(token);

        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final String userId = objectMapper.readTree(payload).get("user").get("id").asText();
            userRepository.save(new RUser(token, userId));
        } catch (final Exception e) {
            LOGGER.error("login() > Error while parsing response to JSON: {}", e.getMessage());
        }

        return token;
    }
}
