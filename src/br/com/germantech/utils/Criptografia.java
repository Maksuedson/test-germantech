package br.com.germantech.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
    public static final String SHA256 = "SHA-256";
    public static final String MD5 = "MD5";

    public static String criptografar(String informacao, String padrao) {
        MessageDigest messageDigest;
        StringBuilder hexString = new StringBuilder();

        try {
            messageDigest = MessageDigest.getInstance(padrao);
            byte[] hash = messageDigest.digest(informacao.getBytes(StandardCharsets.UTF_8));
            hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexString.toString().toUpperCase();
    }
}