package com.billionDollarCompany.bank.common.payloads.request;

import com.billionDollarCompany.bank.common.domain.EncryptionAlgorithm;

public record Security(
        EncryptionAlgorithm algorithm,
        String keyId,
        String encryptedPin
) {
}