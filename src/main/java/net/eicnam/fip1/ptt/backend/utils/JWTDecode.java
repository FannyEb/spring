package net.eicnam.fip1.ptt.backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.eicnam.fip1.ptt.backend.models.RUser;
import net.eicnam.fip1.ptt.backend.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class JWTDecode {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTDecode.class);
    public static String getId(final String token) {
        final String JWTPayload = token.split("\\.")[1];
        final String payload = new String(Base64.getDecoder().decode(JWTPayload));

        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(payload).get("user").get("id").asText();
        } catch (final Exception e) {
            LOGGER.error("getId() > Error while parsing response to JSON: {}", e.getMessage());
        }

        return null;
    }
}
