package com.billionDollarCompany.bank.domain;


import com.billionDollarCompany.bank.common.domain.Currency;
import com.billionDollarCompany.bank.common.domain.RequestContext;
import com.billionDollarCompany.bank.common.domain.ResponseMetadata;
import com.billionDollarCompany.bank.common.payloads.response.ErrorDetails;

public record CheckBalanceResult(
        RequestContext context,
        ResponseMetadata metadata,
        double availableBalance,
        Currency currency,
        ErrorDetails errorDetails
) {
}
