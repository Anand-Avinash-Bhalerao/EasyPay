package com.billionDollarCompany.psp.integration.npci.dto.checkBalance;

import com.billionDollarCompany.psp.common.domain.Currency;

public record NPCICheckBalanceResBodyDTO(
        double availableBalance,
        Currency currency
) {
}