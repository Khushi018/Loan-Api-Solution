package com.luckpay.loan_api_solution.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponseDTO {

    private String loanAccountNumber;
    private String dueDate;
    private Double emiAmount;
}