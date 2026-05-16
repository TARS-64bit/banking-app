package com.bank.service;

import com.bank.dto.AccountResponse;
import com.bank.dto.AddAccountRequest;
import com.bank.dto.AccountDepositRequest;
import com.bank.entity.Account;
import com.bank.exception.AccountNotFoundException;
import com.bank.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AccountService {
    @Inject
    AccountRepository accountRepository;

    @Transactional
    public AccountResponse addAccount(AddAccountRequest request){
        Account newAccount = Account.create(
                request.name(),
                request.accountNumber()
        );
        accountRepository.persist(newAccount);

        return AccountResponse.from(newAccount);
    }

    public AccountResponse getAccount(Long accountId){
        Account account = accountRepository.findById(accountId);
        if(account == null){
            throw new AccountNotFoundException("Account Not Found");
        }
        return AccountResponse.from(account);
    }

    @Transactional
    public AccountResponse deposit(Long id, AccountDepositRequest request){
        Account account = accountRepository.findById(id);
        if(account == null){
            throw new AccountNotFoundException("Account not found: " + id);
        }
        account.deposit(request.amount());
        return AccountResponse.from(account);
    }
}
