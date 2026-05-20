package com.billion_dollor_company.Bank_Server.psp.controller.dto.checkBalance;

import com.billion_dollor_company.Bank_Server.common.domain.Currency;

public record CheckBalanceResBodyDTO(
        double availableBalance,
        Currency currency
) {
}