package com.billionDollarCompany.psp.service;

import com.billionDollarCompany.psp.domain.CheckBalanceCommand;
import com.billionDollarCompany.psp.domain.CheckBalanceResult;
import com.billionDollarCompany.psp.integration.npci.NPCIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PSPServiceImpl implements PSPService{

    @Autowired
    private NPCIClient npciClient;

    @Override
    public CheckBalanceResult initiateBalanceInquiry(CheckBalanceCommand command) {
        return npciClient.initiateCheckBalance(command);
    }
}
