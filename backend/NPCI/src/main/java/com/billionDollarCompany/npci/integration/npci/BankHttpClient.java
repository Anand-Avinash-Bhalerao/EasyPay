package com.billionDollarCompany.npci.integration.npci;

import com.billionDollarCompany.npci.common.payloads.request.BaseRequestDTO;
import com.billionDollarCompany.npci.common.payloads.response.BaseResponseDTO;
import com.billionDollarCompany.npci.domain.CheckBalanceCommand;
import com.billionDollarCompany.npci.domain.CheckBalanceResult;
import com.billionDollarCompany.npci.integration.npci.dto.checkBalance.BankCheckBalanceReqBodyDTO;
import com.billionDollarCompany.npci.integration.npci.dto.checkBalance.BankCheckBalanceResBodyDTO;
import com.billionDollarCompany.npci.integration.npci.mapper.BankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class BankHttpClient implements BankClient {

    @Autowired
    private BankMapper bankMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${bank.base-url}")
    private String baseUrl;

    @Value("${bank.endpoints.checkBalance}")
    private String checkBalanceEndpoint;


    @Override
    public CheckBalanceResult initiateCheckBalance(CheckBalanceCommand command) {

        final String url = baseUrl + checkBalanceEndpoint;

        BaseRequestDTO<BankCheckBalanceReqBodyDTO> request = bankMapper.toNPCICheckBalanceReq(command);

        try {

            // Make call to NPCI for check balance
            ResponseEntity<BaseResponseDTO<BankCheckBalanceResBodyDTO>> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            new HttpEntity<>(request),
                            new ParameterizedTypeReference<>() {
                            }
                    );

            return bankMapper.toCheckBalanceResult(response.getBody());

        } catch (HttpStatusCodeException ex) {

            String responseBody = ex.getResponseBodyAsString();

            BaseResponseDTO<BankCheckBalanceResBodyDTO> errorResponse = null;
            errorResponse = objectMapper.readValue(responseBody, new TypeReference<>() {});


            return bankMapper.toCheckBalanceResult(errorResponse);

        } catch (Exception ex) {
            throw ex;
        }
    }

}