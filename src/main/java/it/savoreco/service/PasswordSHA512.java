package it.savoreco.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class PasswordSHA512 {
    private static final Logger logger = LoggerFactory.getLogger(PasswordSHA512.class);

    public static String SHA512Hash(String password) {
        return sha512(password);
    }


    private static String sha512(String password) {
        MessageDigest sha;
        byte[] hash = null;
        try {
            sha = MessageDigest.getInstance("SHA-512");
            hash = sha.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            logger.error("Hash Algorithm Not Supported", e);
        }
        return convertToHex(Objects.requireNonNull(hash));
    }

    private static String convertToHex(byte[] raw) {
        StringBuilder sb = new StringBuilder();
        for (byte b : raw) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
