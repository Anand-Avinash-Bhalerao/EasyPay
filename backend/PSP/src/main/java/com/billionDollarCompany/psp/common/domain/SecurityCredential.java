package com.billionDollarCompany.psp.common.domain;

public record SecurityCredential(

        EncryptionAlgorithm algorithm,

        String keyId,

        String encryptedPin
) {
}
