package com.billion_dollor_company.Bank_Server.psp.service;

import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceCommand;
import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceResult;
import com.billion_dollor_company.Bank_Server.psp.integration.npci.NPCIClient;
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
