package com.billion_dollor_company.Bank_Server.common.payloads.request;

import com.billion_dollor_company.Bank_Server.common.domain.EncryptionAlgorithm;

public record Security(

        EncryptionAlgorithm algorithm,

        String keyId,

        String encryptedPin
) {
}