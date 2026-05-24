package com.billionDollarCompany.npci.integration.npci.dto.checkBalance;


import com.billionDollarCompany.npci.common.domain.Currency;

public record BankCheckBalanceResBodyDTO(
        double availableBalance,
        Currency currency
) {
}