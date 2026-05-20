package com.billionDollarCompany.psp.domain;

import com.billionDollarCompany.psp.common.domain.RequestContext;
import com.billionDollarCompany.psp.common.domain.SecurityCredential;

public record CheckBalanceCommand(

        RequestContext context,

        SecurityCredential credential,

        String payerVpa

) {
}