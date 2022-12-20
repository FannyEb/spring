package net.eicnam.fip1.sdk.rainbow.conferences;

import net.eicnam.fip1.ptt.backend.utils.DotEnvReader;
import net.eicnam.fip1.sdk.rainbow.RainbowApplication;
import net.eicnam.fip1.sdk.rainbow.utils.Endpoint;
import net.eicnam.fip1.sdk.rainbow.utils.http.HttpHeader;
import net.eicnam.fip1.sdk.rainbow.utils.http.HttpMethod;
import net.eicnam.fip1.sdk.rainbow.utils.http.HttpRequest;

public class Conferences
{
    private final RainbowApplication rainbowApplication = RainbowApplication.getInstance();
    private final DotEnvReader envReader = rainbowApplication.getEnvReader();

    private final String baseUrl = envReader.getValue("API_URL");

    private final HttpHeader header;

    public Conferences() {
        final String token = rainbowApplication.getToken();
        this.header = new HttpHeader().addHeader("Authorization", String.format("Bearer %s", token));
    }

<<<<<<< HEAD
    public void startConference(final String roomId) {
=======
    public String startConference(final String roomId) {
>>>>>>> 033db29d2cca918880a9ce03919bd07d4ac2839f
        final String url = baseUrl + String.format(Endpoint.CONFERENCE_ROOMS_ID_START.toString(), roomId);
        final HttpRequest request = new HttpRequest(url, HttpMethod.POST, header);
        request.connect()
                .send()
                .disconnect();

    }
}
