package com.billionDollarCompany.bank.common.payloads.response;

public record BaseResponseDTO<T>(

        ResponseHeader header,

        T body,

        ErrorDetails error

) {
}
