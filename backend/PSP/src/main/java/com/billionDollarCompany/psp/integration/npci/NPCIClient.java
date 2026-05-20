package com.billionDollarCompany.psp.integration.npci;


import com.billionDollarCompany.psp.domain.CheckBalanceCommand;
import com.billionDollarCompany.psp.domain.CheckBalanceResult;

public interface NPCIClient {

    CheckBalanceResult initiateCheckBalance(CheckBalanceCommand command);

}
