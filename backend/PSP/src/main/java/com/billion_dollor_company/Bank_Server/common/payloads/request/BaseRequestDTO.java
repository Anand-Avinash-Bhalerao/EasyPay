package com.billion_dollor_company.Bank_Server.common.payloads.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequestDTO<T> {

    private RequestHeader header;

    private T body;

    private Security security;

}
