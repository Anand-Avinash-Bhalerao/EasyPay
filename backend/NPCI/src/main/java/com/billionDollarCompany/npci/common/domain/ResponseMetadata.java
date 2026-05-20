package com.billionDollarCompany.npci.common.domain;

public record ResponseMetadata(
        ResponseStatus status,
        String statusCode
) {
}
