package com.billionDollarCompany.npci.common.payloads.request;

import com.billionDollarCompany.npci.common.domain.EncryptionAlgorithm;

public record Security(
        EncryptionAlgorithm algorithm,
        String keyId,
        String encryptedPin
) {
}