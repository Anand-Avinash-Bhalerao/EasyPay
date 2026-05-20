package com.billion_dollor_company.Bank_Server.psp.mappers;

import com.billion_dollor_company.Bank_Server.common.domain.RequestContext;
import com.billion_dollor_company.Bank_Server.common.domain.SecurityCredential;
import com.billion_dollor_company.Bank_Server.common.payloads.request.BaseRequestDTO;
import com.billion_dollor_company.Bank_Server.common.payloads.request.RequestHeader;
import com.billion_dollor_company.Bank_Server.common.payloads.request.Security;
import com.billion_dollor_company.Bank_Server.common.payloads.response.BaseResponseDTO;
import com.billion_dollor_company.Bank_Server.common.payloads.response.ResponseHeader;
import com.billion_dollor_company.Bank_Server.psp.controller.dto.checkBalance.CheckBalanceReqBodyDTO;
import com.billion_dollor_company.Bank_Server.psp.controller.dto.checkBalance.CheckBalanceResBodyDTO;
import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceCommand;
import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PSPMapper {

    /**
     * Maps BaseRequestDTO<CheckBalanceReqBodyDTO> to CheckBalanceCommand
     * Extracts header information to RequestContext, security info to SecurityCredential,
     * and userVpa from body to payerVpa
     */
    @Mapping(source = "header", target = "context")
    @Mapping(source = "security", target = "credential")
    @Mapping(source = "body.userVpa", target = "payerVpa")
    CheckBalanceCommand toCheckBalanceCommand(BaseRequestDTO<CheckBalanceReqBodyDTO> request);

    /**
     * Maps RequestHeader to RequestContext
     */
    RequestContext toRequestContext(RequestHeader header);

    /**
     * Maps Security to SecurityCredential
     */
    SecurityCredential toSecurityCredential(Security security);

    /**
     * Maps CheckBalanceResult to BaseResponseDTO<CheckBalanceResBodyDTO>
     */
    @Mapping(source = "context", target = "header")
    @Mapping(source = "availableBalance", target = "body.availableBalance")
    @Mapping(source = "currency", target = "body.currency")
    @Mapping(source = "errorDetails", target = "error")
    BaseResponseDTO<CheckBalanceResBodyDTO> toCheckBalanceResponse(CheckBalanceResult result);

    /**
     * Maps RequestContext to ResponseHeader
     * Note: requestType, status, and statusCode are not mappable from RequestContext and will be null
     */
    @Mapping(source = "messageId", target = "messageId")
    @Mapping(source = "traceId", target = "traceId")
    @Mapping(source = "timestamp", target = "timestamp")
    @Mapping(source = "requestType", target = "requestType")
    @Mapping(source = "channel", target = "channel")
    @Mapping(source = "pspId", target = "pspId")
    @Mapping(source = "version", target = "version")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "statusCode", ignore = true)
    ResponseHeader toResponseHeader(RequestContext context);

}
