package com.bank.dto;


import com.bank.entity.Account;

public record AccountResponse (
    Long id,
    String name,
    String accountNumber
 ){
    public static AccountResponse from(Account account){
        return new AccountResponse(
                account.getId(),
                account.getName(),
                account.getAccountNumber()
        );
    }
}
