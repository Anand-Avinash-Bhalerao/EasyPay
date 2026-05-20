package com.billionDollarCompany.psp.domain;

import com.billionDollarCompany.psp.common.domain.Currency;
import com.billionDollarCompany.psp.common.domain.RequestContext;
import com.billionDollarCompany.psp.common.domain.ResponseMetadata;
import com.billionDollarCompany.psp.common.payloads.response.ErrorDetails;

public record CheckBalanceResult(

        RequestContext context,

        ResponseMetadata metadata,

        double availableBalance,

        Currency currency,

        ErrorDetails errorDetails

) {
}
