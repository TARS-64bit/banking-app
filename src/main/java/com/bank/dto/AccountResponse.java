package com.bank.dto;


import com.bank.entity.Account;

import java.math.BigDecimal;

public record AccountResponse (
    Long id,
    String name,
    String accountNumber,
    BigDecimal balance
 ){
    public static AccountResponse from(Account account){
        return new AccountResponse(
                account.getId(),
                account.getName(),
                account.getAccountNumber(),
                account.getBalance()
        );
    }
}
