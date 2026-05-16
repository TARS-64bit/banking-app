package com.bank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Table(
        name = "accounts",
        indexes = @Index(name = "idx_account_number", columnList = "accountNumber")
)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true, updatable = false, length = 20)
    private String  accountNumber;
    @Column(nullable = false)
    private BigDecimal balance;

    private Account(String name, String accountNumber){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank");
        }
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
    }

    public void updateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount cannot be null, negative or zero");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount cannot be null, negative or zero");
        }
        if(this.balance.compareTo(amount) < 0){
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance = this.balance.subtract(amount);
    }

    public static Account create(String name, String accountNumber) {
        return new Account(name, accountNumber);
    }
}
