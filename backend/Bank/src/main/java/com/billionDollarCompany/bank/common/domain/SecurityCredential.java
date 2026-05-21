package com.billionDollarCompany.bank.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SecurityCredential {

    EncryptionAlgorithm algorithm;

    String keyId;

    String encryptedPin;
}
