package com.billionDollarCompany.bank.common.payloads.response;

public record ErrorDetails(
        String code,
        String message
) {
}
