package com.billion_dollor_company.Bank_Server.psp.controller;

import com.billion_dollor_company.Bank_Server.common.payloads.request.BaseRequestDTO;
import com.billion_dollor_company.Bank_Server.common.payloads.response.BaseResponseDTO;
import com.billion_dollor_company.Bank_Server.psp.controller.dto.checkBalance.CheckBalanceReqBodyDTO;
import com.billion_dollor_company.Bank_Server.psp.controller.dto.checkBalance.CheckBalanceResBodyDTO;
import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceCommand;
import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceResult;
import com.billion_dollor_company.Bank_Server.psp.mappers.PSPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.billion_dollor_company.Bank_Server.psp.service.PSPService;

/**
 * REST controller for Payment Service Provider operations.
 * Handles incoming PSP-related requests and routes them to appropriate services.
 */
@RestController
@RequestMapping("/psp")
public class PSPController {

    private static final Logger logger = LoggerFactory.getLogger(PSPController.class);

    @Autowired
    private PSPService pspService;

    @Autowired
    private PSPMapper pspMapper;

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
            CheckBalanceCommand command = pspMapper.toCheckBalanceCommand(request);
            CheckBalanceResult result = pspService.initiateBalanceInquiry(command);
            BaseResponseDTO<CheckBalanceResBodyDTO> baseResponse = pspMapper.toCheckBalanceResponse(result);
            return ResponseEntity.ok(baseResponse);
        } catch (Exception e) {
            logger.error("Failed: {} - {}", functionName, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
