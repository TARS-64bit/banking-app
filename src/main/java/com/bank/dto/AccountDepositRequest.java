package com.bank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AccountDepositRequest(
        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount needs to be positive")
        BigDecimal amount
) {}
