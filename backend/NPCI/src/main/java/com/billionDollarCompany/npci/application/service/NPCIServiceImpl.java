package com.billionDollarCompany.npci.application.service;

import com.billionDollarCompany.npci.application.service.cryptography.CryptographyService;
import com.billionDollarCompany.npci.domain.CheckBalanceCommand;
import com.billionDollarCompany.npci.domain.CheckBalanceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NPCIServiceImpl implements NPCIService {

    @Autowired
    CryptographyService cryptographyService;

    @Value("${npci.key.private}")
    private String privateKey;


    @Override
    public CheckBalanceResult initiateBalanceInquiry(CheckBalanceCommand command) {

        String encryptedPin = command.credential().encryptedPin();

        try {
            String decryptedPin = cryptographyService.decrypt(encryptedPin, privateKey);
            System.out.println("Decrypted PIN: " + decryptedPin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
