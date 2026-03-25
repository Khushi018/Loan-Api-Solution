package com.luckpay.loan_api_solution.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private boolean success = false;
    private String code;
    private String message;
    private Instant timestamp;
    private String path;

    public static ErrorResponse of(String code, String message, String path) {
        return new ErrorResponse(false, code, message, Instant.now(), path);
    }
}