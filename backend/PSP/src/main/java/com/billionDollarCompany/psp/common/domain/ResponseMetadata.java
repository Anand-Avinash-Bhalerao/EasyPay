package com.billionDollarCompany.psp.common.domain;

public record ResponseMetadata(
        ResponseStatus status,
        String statusCode
) {
}
