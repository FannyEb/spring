package net.eicnam.fip1.sdk.rainbow;

import net.eicnam.fip1.ptt.backend.utils.DotEnvReader;
import net.eicnam.fip1.sdk.rainbow.authentication.RainbowAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RainbowApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RainbowApplication.class);

    private static RainbowApplication instance;

    private final DotEnvReader envReader;
    private final String token;

    private RainbowApplication() {
        envReader = new DotEnvReader(".");

        final RainbowAuthentication rainbowAuthentication = new RainbowAuthentication(
                envReader.getValue("API_URL"),
                envReader.getValue("API_LOGIN"),
                envReader.getValue("API_PASSWORD"),
                envReader.getValue("API_APP_ID"),
                envReader.getValue("API_APP_SECRET")
        );

        this.token = rainbowAuthentication.getToken();

        LOGGER.info("Rainbow SDK application started w/ token: {}", this.token);
    }

    public static RainbowApplication getInstance() {
        if (instance == null) {
            instance = new RainbowApplication();
        }
        return instance;
    }

    public DotEnvReader getEnvReader() {
        return envReader;
    }

    public String getToken() {
        return token;
    }
}
