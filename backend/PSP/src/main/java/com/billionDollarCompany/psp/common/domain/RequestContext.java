package com.billionDollarCompany.psp.common.domain;

import java.time.Instant;
import java.util.UUID;

public record RequestContext(

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
