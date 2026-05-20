package com.billionDollarCompany.psp.common.payloads.response;

public record ErrorDetails(
        String code,
        String message
) {
}
