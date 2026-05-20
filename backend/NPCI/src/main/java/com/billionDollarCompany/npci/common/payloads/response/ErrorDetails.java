package com.billionDollarCompany.npci.common.payloads.response;

public record ErrorDetails(
        String code,
        String message
) {
}
