package com.billionDollarCompany.bank.application.service;

import com.billionDollarCompany.bank.application.service.cryptography.CryptographyService;
import com.billionDollarCompany.bank.application.service.hashing.HashingService;
import com.billionDollarCompany.bank.domain.CheckBalanceCommand;
import com.billionDollarCompany.bank.domain.CheckBalanceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    CryptographyService cryptographyService;

    @Autowired
    HashingService hashingService;

    @Value("${bank.key.private}")
    private String privateKey;


    @Override
    public CheckBalanceResult initiateBalanceInquiry(CheckBalanceCommand command) {

        String encryptedPin = command.getCredential().getEncryptedPin();

        try {
            String decryptedPin = cryptographyService.decrypt(encryptedPin, privateKey);
            System.out.println("Decrypted PIN: " + decryptedPin);

            String hashedPassword = hashingService.hash(decryptedPin);
            System.out.println("Hashed PIN: " + hashedPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
