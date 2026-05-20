package com.billionDollarCompany.npci.controller;

import com.billionDollarCompany.npci.common.payloads.request.BaseRequestDTO;
import com.billionDollarCompany.npci.common.payloads.response.BaseResponseDTO;
import com.billionDollarCompany.npci.controller.dto.checkBalance.CheckBalanceReqBodyDTO;
import com.billionDollarCompany.npci.controller.dto.checkBalance.CheckBalanceResBodyDTO;
import com.billionDollarCompany.npci.domain.CheckBalanceCommand;
import com.billionDollarCompany.npci.domain.CheckBalanceResult;
import com.billionDollarCompany.npci.mappers.NPCIMapper;
import com.billionDollarCompany.npci.service.NPCIService;
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
 * REST controller for NPCI operations.
 * Handles incoming PSP-related requests and routes them to appropriate services.
 */
@RestController
@RequestMapping("/npci")
public class NPCIController {

    private static final Logger logger = LoggerFactory.getLogger(NPCIController.class);

    @Autowired
    private NPCIService NPCIService;

    @Autowired
    private NPCIMapper npciMapper;

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
            CheckBalanceCommand command = npciMapper.toCheckBalanceCommand(request);
            CheckBalanceResult result = NPCIService.initiateBalanceInquiry(command);
            BaseResponseDTO<CheckBalanceResBodyDTO> baseResponse = npciMapper.toCheckBalanceResponse(result);
            return ResponseEntity.ok(baseResponse);
        } catch (Exception e) {
            logger.error("Failed: {} - {}", functionName, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
