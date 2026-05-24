package com.billionDollarCompany.npci.integration.npci;


import com.billionDollarCompany.npci.domain.CheckBalanceCommand;
import com.billionDollarCompany.npci.domain.CheckBalanceResult;

public interface BankClient {

    CheckBalanceResult initiateCheckBalance(CheckBalanceCommand command);

}
