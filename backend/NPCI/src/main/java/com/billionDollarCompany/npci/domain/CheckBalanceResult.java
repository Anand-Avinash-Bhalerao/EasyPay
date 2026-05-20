package com.billionDollarCompany.npci.domain;


import com.billionDollarCompany.npci.common.domain.Currency;
import com.billionDollarCompany.npci.common.domain.RequestContext;
import com.billionDollarCompany.npci.common.domain.ResponseMetadata;
import com.billionDollarCompany.npci.common.payloads.response.ErrorDetails;

public record CheckBalanceResult(
        RequestContext context,
        ResponseMetadata metadata,
        double availableBalance,
        Currency currency,
        ErrorDetails errorDetails
) {
}
