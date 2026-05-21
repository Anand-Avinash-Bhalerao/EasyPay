package com.billionDollarCompany.bank.common.domain;

public record ResponseMetadata(
        ResponseStatus status,
        String statusCode
) {
}
