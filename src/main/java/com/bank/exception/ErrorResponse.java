package com.bank.exception;

public record ErrorResponse(
        String errorCode,
        String errorMessage
) {}
