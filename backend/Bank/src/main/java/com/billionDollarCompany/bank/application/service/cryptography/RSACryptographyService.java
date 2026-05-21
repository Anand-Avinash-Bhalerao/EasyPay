package com.billionDollarCompany.bank.application.service.cryptography;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class RSACryptographyService implements CryptographyService {

    private static final String RSA_ALGORITHM = "RSA";
    private static final String RSA_CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";

    /**
     * Encrypts plaintext using the provided RSA public key
     *
     * @param plaintext the text to encrypt
     * @param publicKeyStr the Base64 encoded public key
     * @return Base64 encoded encrypted text
     * @throws Exception if encryption fails
     */
    @Override
    public String encrypt(String plaintext, String publicKeyStr) throws Exception {
        PublicKey publicKey = loadPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance(RSA_CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts ciphertext using the provided RSA private key
     *
     * @param ciphertext the Base64 encoded encrypted text
     * @param privateKeyStr the Base64 encoded private key
     * @return decrypted plaintext
     * @throws Exception if decryption fails
     */
    public String decrypt(String ciphertext, String privateKeyStr) throws Exception {
        PrivateKey privateKey = loadPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance(RSA_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Loads a public key from Base64 encoded string
     *
     * @param publicKeyStr the Base64 encoded public key
     * @return PublicKey object
     * @throws Exception if key loading fails
     */
    private PublicKey loadPublicKey(String publicKeyStr) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePublic(spec);
    }

    /**
     * Loads a private key from Base64 encoded string
     *
     * @param privateKeyStr the Base64 encoded private key
     * @return PrivateKey object
     * @throws Exception if key loading fails
     */
    private PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePrivate(spec);
    }
}
