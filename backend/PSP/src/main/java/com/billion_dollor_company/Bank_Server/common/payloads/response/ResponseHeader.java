package com.billion_dollor_company.Bank_Server.common.payloads.response;

import com.billion_dollor_company.Bank_Server.common.domain.Channel;
import com.billion_dollor_company.Bank_Server.common.domain.RequestType;
import com.billion_dollor_company.Bank_Server.common.domain.ResponseStatus;

import java.time.Instant;
import java.util.UUID;

public record ResponseHeader(
        UUID messageId,
        UUID traceId,
        Instant timestamp,
        RequestType requestType,
        ResponseStatus status,
        Channel channel,
        String pspId,
        String statusCode,
        String version
) {
}