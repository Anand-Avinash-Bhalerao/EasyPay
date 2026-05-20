package com.billion_dollor_company.Bank_Server.common.payloads.response;

public record ErrorDetails(
        String code,
        String message
) {
}
