package com.billionDollarCompany.npci.integration.npci.mapper;


import com.billionDollarCompany.npci.common.domain.RequestContext;
import com.billionDollarCompany.npci.common.domain.ResponseMetadata;
import com.billionDollarCompany.npci.common.domain.SecurityCredential;
import com.billionDollarCompany.npci.common.payloads.request.BaseRequestDTO;
import com.billionDollarCompany.npci.common.payloads.request.RequestHeader;
import com.billionDollarCompany.npci.common.payloads.request.Security;
import com.billionDollarCompany.npci.common.payloads.response.BaseResponseDTO;
import com.billionDollarCompany.npci.common.payloads.response.ResponseHeader;
import com.billionDollarCompany.npci.domain.CheckBalanceCommand;
import com.billionDollarCompany.npci.domain.CheckBalanceResult;
import com.billionDollarCompany.npci.integration.npci.dto.checkBalance.BankCheckBalanceReqBodyDTO;
import com.billionDollarCompany.npci.integration.npci.dto.checkBalance.BankCheckBalanceResBodyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankMapper {

    /**
     * Maps CheckBalanceCommand to BaseRequestDTO<NPCICheckBalanceReqBodyDTO>
     * Extracts context to header, credential to security, and payerVpa to body
     */
    @Mapping(source = "context", target = "header")
    @Mapping(source = "credential", target = "security")
    @Mapping(source = "payerVpa", target = "body.userVpa")
    BaseRequestDTO<BankCheckBalanceReqBodyDTO> toNPCICheckBalanceReq(CheckBalanceCommand command);

    /**
     * Maps RequestContext to RequestHeader
     */
    RequestHeader toRequestHeader(RequestContext context);

    /**
     * Maps SecurityCredential to Security
     */
    Security toSecurity(SecurityCredential credential);

    /**
     * Maps BaseResponseDTO<NPCICheckBalanceResBodyDTO> to CheckBalanceResult
     * Extracts response data back to domain model
     */
    @Mapping(source = "header", target = "context")
    @Mapping(source = "body.availableBalance", target = "availableBalance")
    @Mapping(source = "body.currency", target = "currency")
    @Mapping(source = "header", target = "metadata")
    @Mapping(source = "error", target = "errorDetails")
    CheckBalanceResult toCheckBalanceResult(BaseResponseDTO<BankCheckBalanceResBodyDTO> response);

    /**
     * Maps ResponseHeader to RequestContext
     */
    @Mapping(source = "messageId", target = "messageId")
    @Mapping(source = "traceId", target = "traceId")
    @Mapping(target = "idempotencyKey", ignore = true)
    @Mapping(source = "timestamp", target = "timestamp")
    @Mapping(source = "requestType", target = "requestType")
    @Mapping(source = "channel", target = "channel")
    @Mapping(source = "pspId", target = "pspId")
    @Mapping(source = "version", target = "version")
    RequestContext toRequestContext(ResponseHeader header);

     /**
     * Maps status and statusCode from ResponseHeader to ResponseMetadata
     */
    @Mapping(source = "status", target = "status")
    @Mapping(source = "statusCode", target = "statusCode")
    ResponseMetadata toResponseMetadata(ResponseHeader header);

}