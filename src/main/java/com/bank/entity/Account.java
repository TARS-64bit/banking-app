package com.bank.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
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

    private Account(String name, String accountNumber){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank");
        }
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public void updateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;
    }

    public static Account create(String name, String accountNumber) {
        return new Account(name, accountNumber);
    }
}
