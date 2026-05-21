package com.billionDollarCompany.bank.application.service.cryptography;

public interface CryptographyService {

    public String encrypt(String plainText, String publicKeyStr) throws Exception;

    public String decrypt(String ciphertext, String privateKeyStr) throws Exception;

}
