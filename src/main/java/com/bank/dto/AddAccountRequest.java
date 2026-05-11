package com.bank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddAccountRequest(
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 120, message = "Name must be at most 120 characters")
        String name,
        @NotBlank(message = "Account number cannot be blank")
        @Size(max = 20, message = "Account number must be at most 20 characters")
        String accountNumber
){}
