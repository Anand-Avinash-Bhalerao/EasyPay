package com.billion_dollor_company.Bank_Server.common.domain;

public record SecurityCredential(

        EncryptionAlgorithm algorithm,

        String keyId,

        String encryptedPin
) {
}
