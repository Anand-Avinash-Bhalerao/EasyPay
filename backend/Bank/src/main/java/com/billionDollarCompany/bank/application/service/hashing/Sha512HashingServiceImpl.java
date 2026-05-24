package com.billionDollarCompany.bank.application.service.hashing;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class Sha512HashingServiceImpl implements HashingService {

    @Override
    public String hash(String originalString) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            byte[] hashedBytes = md.digest(
                    originalString.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not available", e);
        }
    }
}
