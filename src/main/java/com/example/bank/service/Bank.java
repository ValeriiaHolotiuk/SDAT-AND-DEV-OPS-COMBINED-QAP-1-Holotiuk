package com.example.bank.service;

import com.example.bank.exception.AccountNotFoundException;
import com.example.bank.model.Account;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();

    public Account createAccount(String id, String owner, BigDecimal openingBalance) {
        if (accounts.containsKey(id)) throw new IllegalArgumentException("Account already exists: " + id);
        Account acc = new Account(id, owner, openingBalance);
        accounts.put(id, acc);
        return acc;
    }

    public Account find(String id) {
        Account acc = accounts.get(id);
        if (acc == null) throw new AccountNotFoundException("No account: " + id);
        return acc;
    }

    public void transfer(String fromId, String toId, BigDecimal amount) {
        Account from = find(fromId);
        Account to = find(toId);
        from.withdraw(amount);
        to.deposit(amount);
    }

    public Map<String, Account> viewAll() {
        return Collections.unmodifiableMap(accounts);
    }
}
