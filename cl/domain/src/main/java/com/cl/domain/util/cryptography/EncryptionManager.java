package com.cl.domain.util.cryptography;


import android.os.Build;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;


// we only need the public key for encryption.
public class EncryptionManager {
    private final String keyName;
    private PublicKey publicKey;

    private String encode(byte[] data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getEncoder().encodeToString(data);
        }
        return null;
    }

    private byte[] decode(String encodedStr) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getDecoder().decode(encodedStr);
        }
        return null;
    }

    public EncryptionManager(String key, String name) {

        System.out.println("In the constructor: the values of the name is "+name+" and the value of the key is "+key);
        this.keyName = name;
        initWithStrings(key);
    }

    // this function is used to initialize the public key.
    private void initWithStrings(String key) {
        try {
            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            publicKey = keyFactory.generatePublic(keySpecPublic);
        } catch (Exception ignored) {
        }
    }

    public void printKeys() {
        System.out.println("The public key named:'" + keyName + "'is :" + encode(publicKey.getEncoded()));
    }

    public String getEncryptedMessage(String message) throws Exception {
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return encode(encryptedBytes);
    }
}
