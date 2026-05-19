package com.bank.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record TransactionEvent(
        Long transactionId,
        String fromAccount,
        String toAccount,
        Double amount
) {}
