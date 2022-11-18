package net.eicnam.fip1.ptt.backend.services;

import lombok.AllArgsConstructor;
import net.eicnam.fip1.ptt.backend.utils.DotEnvReader;
import net.eicnam.fip1.sdk.rainbow.RainbowApplication;
import net.eicnam.fip1.sdk.rainbow.authentication.RainbowAuthentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    public String login(final String email, final String password) {

        final RainbowApplication rainbowApplication = RainbowApplication.getInstance();
        final DotEnvReader env = rainbowApplication.getEnvReader();

        return new RainbowAuthentication(
                env.getValue("API_URL"),
                email,
                password,
                env.getValue("API_APP_ID"),
                env.getValue("API_APP_SECRET")
        ).getToken();
    }
}
