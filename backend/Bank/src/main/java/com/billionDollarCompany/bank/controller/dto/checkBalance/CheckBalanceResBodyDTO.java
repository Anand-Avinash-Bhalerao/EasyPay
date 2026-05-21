package com.billionDollarCompany.bank.controller.dto.checkBalance;


import com.billionDollarCompany.bank.common.domain.Currency;

public record CheckBalanceResBodyDTO(
        double availableBalance,
        Currency currency
) {
}