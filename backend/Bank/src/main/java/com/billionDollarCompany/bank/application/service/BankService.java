package com.billionDollarCompany.bank.application.service;


import com.billionDollarCompany.bank.domain.CheckBalanceCommand;
import com.billionDollarCompany.bank.domain.CheckBalanceResult;

public interface BankService {

    CheckBalanceResult initiateBalanceInquiry(CheckBalanceCommand command);

}
