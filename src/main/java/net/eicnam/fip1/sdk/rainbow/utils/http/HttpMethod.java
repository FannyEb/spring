package net.eicnam.fip1.sdk.rainbow.utils.http;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    private final String name;

    HttpMethod(final String name) {
        this.name = name;
    }

    public boolean equalsName(final String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
