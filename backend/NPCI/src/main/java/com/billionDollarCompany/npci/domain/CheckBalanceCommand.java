package com.billionDollarCompany.npci.domain;


import com.billionDollarCompany.npci.common.domain.RequestContext;
import com.billionDollarCompany.npci.common.domain.SecurityCredential;

public record CheckBalanceCommand(

        RequestContext context,

        SecurityCredential credential,

        String payerVpa

) {
}