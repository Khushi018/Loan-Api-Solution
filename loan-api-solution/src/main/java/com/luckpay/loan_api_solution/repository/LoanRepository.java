package com.luckpay.loan_api_solution.repository;

import com.luckpay.loan_api_solution.entity.LoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanAccount, Long> {
    boolean existsByLoanAccountNumber(String loanAccountNumber);
}