package com.billion_dollor_company.Bank_Server.psp.integration.npci.dto.checkBalance;

import com.billion_dollor_company.Bank_Server.common.domain.Currency;

public record NPCICheckBalanceResBodyDTO(
        double availableBalance,
        Currency currency
) {
}