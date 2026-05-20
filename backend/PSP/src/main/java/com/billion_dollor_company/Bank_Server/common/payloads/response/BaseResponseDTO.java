package com.billion_dollor_company.Bank_Server.common.payloads.response;

public record BaseResponseDTO<T>(

        ResponseHeader header,

        T body,

        ErrorDetails error

) {
}
