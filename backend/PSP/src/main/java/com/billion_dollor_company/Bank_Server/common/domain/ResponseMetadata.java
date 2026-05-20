package com.billion_dollor_company.Bank_Server.common.domain;

public record ResponseMetadata(
        ResponseStatus status,
        String statusCode
) {
}
