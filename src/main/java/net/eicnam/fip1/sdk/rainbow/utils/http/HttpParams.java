package net.eicnam.fip1.sdk.rainbow.utils.http;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpParams {

    Map<String, String> params = new HashMap<>();

    public HttpParams() {}

    public HttpParams(final HttpParams params) {
        this(params.getParamsMap());
    }

    public HttpParams(final Map<String, String> params) {
        this.params.putAll(params);
    }

    public HttpParams addParam(final String key, final String value) {
        this.params.put(key, value);
        return this;
    }

    public String getParamsString() {
        final StringBuilder result = new StringBuilder();

        for (final Map.Entry<String, String> entry : this.params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            result.append("&");
        }

        final String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    public Map<String, String> getParamsMap() {
        return this.params;
    }
}
