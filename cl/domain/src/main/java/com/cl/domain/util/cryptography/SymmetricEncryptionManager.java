package com.cl.domain.util.cryptography;

import android.os.Build;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SymmetricEncryptionManager {
    private final SecretKey secretKey;

    public SymmetricEncryptionManager() {
        this.secretKey = getSecretKey();
    }

    private SecretKey getSecretKey() {
        try {
            String password = "mD7!nA4q8Jr@2Zt9&Ls#";
            byte[] keyBytes = password.getBytes(StandardCharsets.UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            keyBytes = sha.digest(keyBytes);
            keyBytes = java.util.Arrays.copyOf(keyBytes, 16); // Use only first 128 bits (16 bytes)
            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
            System.out.println("returning secret key");
            return secretKey;
        } catch (Exception e) {
            return null;
        }
    }

    public String encryptText(String text) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return Base64.getEncoder().encodeToString(encryptedBytes);
            }
        } catch (Exception ignored) {
        }
        return null;

    }

    public String decryptText(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                decodedBytes = Base64.getDecoder().decode(encryptedText);
            }
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception ignored) {
        }
        return null;
    }


}
