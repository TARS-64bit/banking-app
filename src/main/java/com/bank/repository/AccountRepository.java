package com.bank.repository;

import com.bank.entity.Account;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class AccountRepository implements PanacheRepository<Account> {
}
