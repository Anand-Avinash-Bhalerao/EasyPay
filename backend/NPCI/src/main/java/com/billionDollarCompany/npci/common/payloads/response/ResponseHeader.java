package com.billionDollarCompany.npci.common.payloads.response;

import com.billionDollarCompany.npci.common.domain.Channel;
import com.billionDollarCompany.npci.common.domain.RequestType;
import com.billionDollarCompany.npci.common.domain.ResponseStatus;

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