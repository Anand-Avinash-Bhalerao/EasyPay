package com.billionDollarCompany.npci.service;

import com.billionDollarCompany.npci.domain.CheckBalanceCommand;
import com.billionDollarCompany.npci.domain.CheckBalanceResult;
import org.springframework.stereotype.Service;

@Service
public class NPCIServiceImpl implements NPCIService {


    @Override
    public CheckBalanceResult initiateBalanceInquiry(CheckBalanceCommand command) {
        return null;
    }
}
