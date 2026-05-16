package com.bank.dto;

public record TransferResponse(
        String referenceNumber,
        String status
) {}
