package com.bank.service;

import com.bank.dto.TransferRequest;
import com.bank.dto.TransferResponse;
import com.bank.entity.Account;
import com.bank.exception.AccountNotFoundException;
import com.bank.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransferService {

    @Inject
    AccountRepository accountRepository;

    @Transactional
    public TransferResponse transfer(TransferRequest request){
        Account fromAccount = accountRepository.findById(request.fromAccountId());
        Account toAccount = accountRepository.findById(request.toAccountId());

        if(request.fromAccountId().equals(request.toAccountId())){
            throw new IllegalArgumentException("From and to accounts cannot be the same");
        }

        if(fromAccount == null || toAccount == null){
            throw new AccountNotFoundException("Account not found: " +
                    (fromAccount == null? request.fromAccountId() : request.toAccountId())
            );
        }

        fromAccount.withdraw(request.amount());
        toAccount.deposit(request.amount());

        return new TransferResponse("Test1", "Completed");
    }
}
