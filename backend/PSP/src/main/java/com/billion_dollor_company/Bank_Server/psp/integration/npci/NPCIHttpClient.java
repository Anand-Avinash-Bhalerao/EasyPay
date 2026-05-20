package com.billion_dollor_company.Bank_Server.psp.integration.npci;

import com.billion_dollor_company.Bank_Server.common.payloads.request.BaseRequestDTO;
import com.billion_dollor_company.Bank_Server.common.payloads.response.BaseResponseDTO;
import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceCommand;
import com.billion_dollor_company.Bank_Server.psp.domain.CheckBalanceResult;
import com.billion_dollor_company.Bank_Server.psp.integration.npci.dto.checkBalance.NPCICheckBalanceReqBodyDTO;
import com.billion_dollor_company.Bank_Server.psp.integration.npci.dto.checkBalance.NPCICheckBalanceResBodyDTO;
import com.billion_dollor_company.Bank_Server.psp.integration.npci.mapper.NPCIMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class NPCIHttpClient implements NPCIClient {

    @Autowired
    private NPCIMapper npciMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${npci.base-url}")
    private String baseUrl;

    @Value("${npci.endpoints.checkBalance}")
    private String checkBalanceEndpoint;


    @Override
    public CheckBalanceResult initiateCheckBalance(CheckBalanceCommand command) {

        final String url = baseUrl + checkBalanceEndpoint;

        BaseRequestDTO<NPCICheckBalanceReqBodyDTO> request = npciMapper.toNPCICheckBalanceReq(command);

        try {

            ResponseEntity<BaseResponseDTO<NPCICheckBalanceResBodyDTO>> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            new HttpEntity<>(request),
                            new ParameterizedTypeReference<>() {
                            }
                    );

            return npciMapper.toCheckBalanceResult(response.getBody());

        } catch (HttpStatusCodeException ex) {

            String responseBody = ex.getResponseBodyAsString();

            BaseResponseDTO<NPCICheckBalanceResBodyDTO> errorResponse = null;
            try {
                errorResponse = objectMapper.readValue(responseBody, new TypeReference<>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            return npciMapper.toCheckBalanceResult(errorResponse);

        } catch (Exception ex) {
            throw ex;
        }
    }

}