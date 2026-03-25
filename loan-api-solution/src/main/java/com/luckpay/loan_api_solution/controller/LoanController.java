package com.luckpay.loan_api_solution.controller;

import com.luckpay.loan_api_solution.dto.LoanResponseDTO;
import com.luckpay.loan_api_solution.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }
    @GetMapping("/{loanAccountNumber}")
    public ResponseEntity<LoanResponseDTO> getLoan(
            @PathVariable String loanAccountNumber) {

        return ResponseEntity.ok(
                service.getLoanDetails(loanAccountNumber)
        );
    }
}