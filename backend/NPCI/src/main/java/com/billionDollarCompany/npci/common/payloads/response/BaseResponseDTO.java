package com.billionDollarCompany.npci.common.payloads.response;

public record BaseResponseDTO<T>(

        ResponseHeader header,

        T body,

        ErrorDetails error

) {
}
