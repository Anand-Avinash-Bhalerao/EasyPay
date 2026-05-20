package com.billionDollarCompany.npci.common.domain;

public record SecurityCredential(

        EncryptionAlgorithm algorithm,

        String keyId,

        String encryptedPin
) {
}
