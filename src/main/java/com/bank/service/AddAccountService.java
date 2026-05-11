package com.bank.service;

import com.bank.dto.AccountResponse;
import com.bank.dto.AddAccountRequest;
import com.bank.entity.Account;
import com.bank.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AddAccountService {
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
}
