package net.eicnam.fip1.ptt.backend.utils;

import java.util.Base64;

public class JWTDecode {
    public static String decode(final String token) {
        final String JWTPayload = token.split("\\.")[1];
        return new String(Base64.getDecoder().decode(JWTPayload));
    }
}
