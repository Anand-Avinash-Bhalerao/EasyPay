package com.billionDollarCompany.bank.domain;


import com.billionDollarCompany.bank.common.domain.RequestContext;
import com.billionDollarCompany.bank.common.domain.SecurityCredential;
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