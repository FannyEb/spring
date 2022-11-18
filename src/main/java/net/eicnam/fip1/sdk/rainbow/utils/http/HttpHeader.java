package net.eicnam.fip1.sdk.rainbow.utils.http;

import java.util.HashMap;
import java.util.Map;

public class HttpHeader {
    private final Map<String, String> header = new HashMap<>();

    public HttpHeader() {}

    public HttpHeader(final HttpHeader header) {
        this(header.getHeaderMap());
    }

    public HttpHeader(final Map<String, String> header) {
        this.header.putAll(header);
    }

    public HttpHeader addHeader(final String key, final String value) {
        this.header.put(key, value);
        return this;
    }

    public Map<String, String> getHeaderMap() {
        return this.header;
    }
}
