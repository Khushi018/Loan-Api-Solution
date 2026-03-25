package com.luckpay.loan_api_solution.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class LoanAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loanAccountNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id")
    private List<EmiDetails> emiDetails;
}