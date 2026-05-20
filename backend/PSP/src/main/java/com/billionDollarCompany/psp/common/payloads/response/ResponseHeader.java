package com.billionDollarCompany.psp.common.payloads.response;

import com.billionDollarCompany.psp.common.domain.Channel;
import com.billionDollarCompany.psp.common.domain.RequestType;
import com.billionDollarCompany.psp.common.domain.ResponseStatus;

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