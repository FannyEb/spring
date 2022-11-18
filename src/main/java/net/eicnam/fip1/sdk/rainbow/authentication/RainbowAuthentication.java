package net.eicnam.fip1.sdk.rainbow.authentication;

import net.eicnam.fip1.sdk.rainbow.utils.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.eicnam.fip1.sdk.rainbow.utils.http.HttpHeader;
import net.eicnam.fip1.sdk.rainbow.utils.http.HttpMethod;
import net.eicnam.fip1.sdk.rainbow.utils.http.HttpRequest;
import net.eicnam.fip1.sdk.rainbow.utils.EncodeAndHash;

public class RainbowAuthentication {
    private static final Logger LOGGER = LoggerFactory.getLogger(RainbowAuthentication.class);

    private String token;

    private final String baseUrl;
    private final String auth;
    private final String appAuth;

    public RainbowAuthentication(String baseUrl, String login, String password, String appId, String appSecret) {
        this.baseUrl = baseUrl;
        this.auth = EncodeAndHash.base64(String.format("%s:%s", login, password));
        this.appAuth = EncodeAndHash.base64(String.format("%s:%s", appId, EncodeAndHash.hash(appSecret + password)));
        saveToken();
    }

    private void saveToken() {
        final HttpHeader httpHeader = new HttpHeader()
                .addHeader("Authorization", String.format("Basic %s", auth))
                .addHeader("x-rainbow-app-auth", String.format("Basic %s", appAuth));

        final HttpRequest httpRequest = new HttpRequest(baseUrl + Endpoint.AUTH_LOGIN, HttpMethod.GET, httpHeader);

        final String response = httpRequest.connect()
                .send()
                .disconnect()
                .getResponse();

        final ObjectMapper mapper = new ObjectMapper();
        try {
            final JsonNode jsonNode = mapper.readTree(response);
            this.token = jsonNode.get("token").asText();

        } catch (final Exception e) {
            LOGGER.error("saveToken() > Error while parsing response to JSON: {}", e.getMessage());
        }
    }

    public String getToken() {
        return this.token;
    }

}
