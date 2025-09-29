package com.example.bank.model;

import com.example.bank.exception.InvalidAmountException;
import com.example.bank.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private final String id;
    private final String owner;
    private BigDecimal balance;

    public Account(String id, String owner, BigDecimal openingBalance) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id required");
        if (owner == null || owner.isBlank()) throw new IllegalArgumentException("Owner required");
        if (openingBalance == null || openingBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Opening balance must be >= 0");
        this.id = id;
        this.owner = owner;
        this.balance = openingBalance;
    }
    
    public String getId() { return id; }
    public String getOwner() { return owner; }
    public BigDecimal getBalance() { return balance; }

    public void deposit(BigDecimal amount) {
        requirePositive(amount);
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        requirePositive(amount);
        if (balance.compareTo(amount) < 0)
            throw new InsufficientFundsException("Not enough funds");
        balance = balance.subtract(amount);
    }

    private void requirePositive(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidAmountException("Amount must be > 0");
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
