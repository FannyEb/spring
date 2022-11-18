package net.eicnam.fip1.sdk.rainbow.utils;

import net.eicnam.fip1.sdk.rainbow.authentication.RainbowAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.xml.bind.DatatypeConverter;

/**
 * Utils class for Encode And Hash a given data
 */
public final class EncodeAndHash {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncodeAndHash.class);

    private EncodeAndHash(){}

    /**
     * Utilities to encrypt a given data with SHA-256
     * @param data - data to encrypt
     * @return SHA-256 encrypted data or null when unknown error occurs
     */
    public static String hash(final String data) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = messageDigest.digest(data.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hash);

        } catch (final NoSuchAlgorithmException e) {
            LOGGER.error("hash() > Error hashing data: " + e.getMessage());
            return null;
        }
    }

    /**
     * Utilities to encode a given data in base64
     * @param data - data to encode
     * @return base 64 encoded data
     */
    public static String base64(final String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }
}
