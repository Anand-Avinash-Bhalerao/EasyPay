package com.billion_dollor_company.Bank_Server.psp.domain;

import com.billion_dollor_company.Bank_Server.common.domain.Currency;
import com.billion_dollor_company.Bank_Server.common.domain.RequestContext;
import com.billion_dollor_company.Bank_Server.common.domain.ResponseMetadata;
import com.billion_dollor_company.Bank_Server.common.payloads.response.ErrorDetails;

import java.math.BigDecimal;

public record CheckBalanceResult(

        RequestContext context,

        ResponseMetadata metadata,

        double availableBalance,

        Currency currency,

        ErrorDetails errorDetails

) {
}
