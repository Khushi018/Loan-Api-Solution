package com.luckpay.loan_api_solution.integration;

import com.luckpay.loan_api_solution.entity.LoanAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoanApiIntegration {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public LoanApiIntegration(RestTemplate restTemplate, @Value("${base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }
    public LoanAccount fetchLoan(String loanAccountNumber) {
        return restTemplate.getForObject(
                baseUrl + loanAccountNumber,
                LoanAccount.class
        );
    }
}