package com.billionDollarCompany.psp.common.payloads.request;

import com.billionDollarCompany.psp.common.domain.Channel;
import com.billionDollarCompany.psp.common.domain.RequestType;

import java.time.Instant;
import java.util.UUID;

public record RequestHeader(

        UUID messageId,

        UUID traceId,

        UUID idempotencyKey,

        Instant timestamp,

        RequestType requestType,

        Channel channel,

        String pspId,

        String version

) {
}
