package net.eicnam.fip1.ptt.backend.services;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import net.eicnam.fip1.ptt.backend.models.RUser;
import net.eicnam.fip1.ptt.backend.repositories.UserRepository;
import net.eicnam.fip1.ptt.backend.utils.DotEnvReader;
import net.eicnam.fip1.ptt.backend.utils.JWTDecode;

import net.eicnam.fip1.sdk.rainbow.RainbowApplication;
import net.eicnam.fip1.sdk.rainbow.authentication.RainbowAuthentication;

@AllArgsConstructor
@Service
public class AuthService {
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

        final String userId = JWTDecode.getId(token);
        userRepository.save(new RUser(token, userId));

        return token;
    }
}
