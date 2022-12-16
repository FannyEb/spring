package net.eicnam.fip1.sdk.rainbow.utils.http;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HttpRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequest.class);

    private URL url;
    private final HttpMethod method;
    private final HttpHeader header;
    private HttpURLConnection con;

    private String response;

    public HttpRequest(final String url, final HttpMethod method, final HttpHeader header) {
        this(url, method, header, new HttpParams());
    }

    public HttpRequest(final String url, final HttpMethod method, final HttpHeader header, final HttpParams params) {
        try {

            this.url = params.getParamsString() == null ? new URL(url) : new URL(url + "?" + params.getParamsString());
            LOGGER.info(this.url.toString());
        } catch (final MalformedURLException e) {
            LOGGER.error("HttpRequest() > Malformed URL Exception: {}", e.getMessage());
            this.url = null;
        }
        this.method = method;
        this.header = header;
    }

    public HttpRequest connect() {
        try {
            this.con = (HttpURLConnection) this.url.openConnection();
            con.setRequestMethod(method.toString());
        } catch (final IOException e) {
            LOGGER.error("connect() > Error while connecting to the server");
        }
        return this;
    }

    public HttpRequest disconnect() {
        this.con.disconnect();
        return this;
    }

    public HttpRequest send() {
        try {
            for (final Map.Entry<String, String> headerElement : header.getHeaderMap().entrySet()) {
                this.con.setRequestProperty(headerElement.getKey(), headerElement.getValue());
            }

            con.setDoInput(true);
            con.setDoOutput(true);

            final BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );

            response = bufferedReader.readLine();

        } catch (final IOException e) {
            LOGGER.error("send() > Error while sending request: {}", e.getMessage());
        }
        return this;
    }

    public String getResponse() {
        return response;
    }
}
