package com.billionDollarCompany.npci.application.service;

import com.billionDollarCompany.npci.application.service.cryptography.CryptographyService;
import com.billionDollarCompany.npci.domain.CheckBalanceCommand;
import com.billionDollarCompany.npci.domain.CheckBalanceResult;
import com.billionDollarCompany.npci.integration.npci.BankClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NPCIServiceImpl implements NPCIService {

    @Autowired
    CryptographyService cryptographyService;

    @Autowired
    BankClient bankClient;

    @Value("${npci.key.private}")
    private String npciPrivateKey;

    @Value("${bank.key.public}")
    private String bankPublicKey;


    @Override
    public CheckBalanceResult initiateBalanceInquiry(CheckBalanceCommand command) {

        String encryptedPin = command.getCredential().getEncryptedPin();

        try {
            String decryptedPin = cryptographyService.decrypt(encryptedPin, npciPrivateKey);

            String encryptedPinForBank = cryptographyService.encrypt(decryptedPin, bankPublicKey);

            command.getCredential().setEncryptedPin(encryptedPinForBank);

            return bankClient.initiateCheckBalance(command);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
