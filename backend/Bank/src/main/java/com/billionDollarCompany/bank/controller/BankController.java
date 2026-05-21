package com.billionDollarCompany.bank.controller;

import com.billionDollarCompany.bank.common.payloads.request.BaseRequestDTO;
import com.billionDollarCompany.bank.common.payloads.response.BaseResponseDTO;
import com.billionDollarCompany.bank.controller.dto.checkBalance.CheckBalanceReqBodyDTO;
import com.billionDollarCompany.bank.controller.dto.checkBalance.CheckBalanceResBodyDTO;
import com.billionDollarCompany.bank.domain.CheckBalanceCommand;
import com.billionDollarCompany.bank.domain.CheckBalanceResult;
import com.billionDollarCompany.bank.mappers.BankMapper;
import com.billionDollarCompany.bank.application.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for Bank operations.
 * Handles incoming PSP-related requests and routes them to appropriate services.
 */
@RestController
@RequestMapping("/bank")
public class BankController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);

    @Autowired
    private BankService bankService;

    @Autowired
    private BankMapper bankMapper;

    /**
     * Retrieves the current balance for a given account.
     *
     * @param request Contains account details needed to check balance
     * @return CheckBalanceResDTO with the account balance information
     */
    @PostMapping("/checkBalance")
    public ResponseEntity<BaseResponseDTO<CheckBalanceResBodyDTO>> checkBalance(@RequestBody BaseRequestDTO<CheckBalanceReqBodyDTO> request) {
        final String functionName = "checkBalance(@RequestBody BaseRequestDTO<CheckBalanceReqBodyDTO> request)";
        logger.info("Initiated: {}", functionName);

        try {
            CheckBalanceCommand command = bankMapper.toCheckBalanceCommand(request);
            CheckBalanceResult result = bankService.initiateBalanceInquiry(command);
            BaseResponseDTO<CheckBalanceResBodyDTO> baseResponse = bankMapper.toCheckBalanceResponse(result);
            return ResponseEntity.ok(baseResponse);
        } catch (Exception e) {
            logger.error("Failed: {} - {}", functionName, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
