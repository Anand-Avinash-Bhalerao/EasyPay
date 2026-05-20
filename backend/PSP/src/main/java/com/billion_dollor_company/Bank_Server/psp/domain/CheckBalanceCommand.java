package com.billion_dollor_company.Bank_Server.psp.domain;

import com.billion_dollor_company.Bank_Server.common.domain.RequestContext;
import com.billion_dollor_company.Bank_Server.common.domain.SecurityCredential;

public record CheckBalanceCommand(

        RequestContext context,

        SecurityCredential credential,

        String payerVpa

) {
}