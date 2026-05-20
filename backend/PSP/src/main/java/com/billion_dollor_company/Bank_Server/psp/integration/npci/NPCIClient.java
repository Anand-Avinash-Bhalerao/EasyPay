package com.billion_dollor_company.Bank_Server.psp.integration.npci;


import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceCommand;
import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceResult;

public interface NPCIClient {

    CheckBalanceResult initiateCheckBalance(CheckBalanceCommand command);

}
