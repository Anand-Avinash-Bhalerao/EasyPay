package com.billionDollarCompany.psp.common.payloads.request;

import com.billionDollarCompany.psp.common.domain.EncryptionAlgorithm;

public record Security(

        EncryptionAlgorithm algorithm,

        String keyId,

        String encryptedPin
) {
}