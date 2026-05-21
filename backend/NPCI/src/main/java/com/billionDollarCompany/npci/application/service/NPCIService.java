package com.billionDollarCompany.npci.application.service;


import com.billionDollarCompany.npci.domain.CheckBalanceCommand;
import com.billionDollarCompany.npci.domain.CheckBalanceResult;

public interface NPCIService {

    CheckBalanceResult initiateBalanceInquiry(CheckBalanceCommand command);

}
