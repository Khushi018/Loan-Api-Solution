package com.luckpay.loan_api_solution.service;

import com.luckpay.loan_api_solution.dto.LoanResponseDTO;
import com.luckpay.loan_api_solution.entity.EmiDetails;
import com.luckpay.loan_api_solution.entity.LoanAccount;
import com.luckpay.loan_api_solution.integration.LoanApiIntegration;
import com.luckpay.loan_api_solution.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
public class LoanService {

    private static final Logger log = LoggerFactory.getLogger(LoanService.class);

    private final LoanApiIntegration loanIntegration;
    private final LoanRepository loanRepository;

    public LoanService(LoanApiIntegration loanIntegration, LoanRepository loanRepository) {
        this.loanIntegration = loanIntegration;
        this.loanRepository = loanRepository;
    }

    public LoanResponseDTO getLoanDetails(String loanAccountNumber) {

        log.info("Get loan details request received. loanAccountNumber={}", loanAccountNumber);
        
        LoanAccount loan = loanIntegration.fetchLoan(loanAccountNumber);

        if (!loanRepository.existsByLoanAccountNumber(loanAccountNumber)) {
            log.debug("Persisting loan account fetched first time. loanAccountNumber={}", loanAccountNumber);
            loanRepository.save(loan);
        } else {
            log.debug("Loan account already persisted; skipping save. loanAccountNumber={}", loanAccountNumber);
        }

        EmiDetails dueEmi = loan.getEmiDetails()
                .stream()
                .filter(EmiDetails::getDueStatus)
                .findFirst()
                .orElseThrow(() -> {
                    return new RuntimeException("No due EMI found");
                });

        String dueDate = convertMonthToDate(dueEmi.getMonth());

        return new LoanResponseDTO(
                loan.getLoanAccountNumber(),
                dueDate,
                dueEmi.getEmiAmount()
        );
    }


    private String convertMonthToDate(String monthYear) {
        DateTimeFormatter inputFormatter =
                DateTimeFormatter.ofPattern("MMMM yyyy");
        DateTimeFormatter outputFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");
        YearMonth yearMonth = YearMonth.parse(monthYear, inputFormatter);

        return yearMonth.atDay(1).format(outputFormatter);
    }
}