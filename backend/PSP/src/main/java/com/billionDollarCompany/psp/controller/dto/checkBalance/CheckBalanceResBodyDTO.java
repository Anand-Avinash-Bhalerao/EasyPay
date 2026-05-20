package com.billionDollarCompany.psp.controller.dto.checkBalance;

import com.billionDollarCompany.psp.common.domain.Currency;

public record CheckBalanceResBodyDTO(
        double availableBalance,
        Currency currency
) {
}