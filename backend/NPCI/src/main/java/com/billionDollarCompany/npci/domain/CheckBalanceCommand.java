package com.billionDollarCompany.npci.domain;


import com.billionDollarCompany.npci.common.domain.RequestContext;
import com.billionDollarCompany.npci.common.domain.SecurityCredential;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CheckBalanceCommand{

        RequestContext context;

        SecurityCredential credential;

        String payerVpa;

}