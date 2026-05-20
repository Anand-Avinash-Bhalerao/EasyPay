package com.billion_dollor_company.Bank_Server.common.payloads.request;

import com.billion_dollor_company.Bank_Server.common.domain.Channel;
import com.billion_dollor_company.Bank_Server.common.domain.RequestType;

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
