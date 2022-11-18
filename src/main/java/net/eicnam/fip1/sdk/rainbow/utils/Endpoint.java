package net.eicnam.fip1.sdk.rainbow.utils;

public enum Endpoint
{
    AUTH_LOGIN("/authentication/v1.0/login"),
    ENDUSER_ROOMS("/enduser/v1.0/rooms");

    private final String endpoint;

    Endpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public boolean equalsName(final String otherName) {
        return endpoint.equals(otherName);
    }

    @Override
    public String toString() {
        return this.endpoint;
    }
}