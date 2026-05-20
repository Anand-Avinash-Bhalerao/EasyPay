package com.billion_dollor_company.Bank_Server.psp.service;

import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceCommand;
import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceResult;

public interface PSPService {

    CheckBalanceResult initiateBalanceInquiry(CheckBalanceCommand command);

}
