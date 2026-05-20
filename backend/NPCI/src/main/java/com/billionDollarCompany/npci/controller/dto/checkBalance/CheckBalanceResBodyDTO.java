package com.billionDollarCompany.npci.controller.dto.checkBalance;


import com.billionDollarCompany.npci.common.domain.Currency;

public record CheckBalanceResBodyDTO(
        double availableBalance,
        Currency currency
) {
}