package com.billionDollarCompany.psp.service;

import com.billionDollarCompany.psp.domain.CheckBalanceCommand;
import com.billionDollarCompany.psp.domain.CheckBalanceResult;

public interface PSPService {

    CheckBalanceResult initiateBalanceInquiry(CheckBalanceCommand command);

}
