package com.example.bank;

import com.example.bank.exception.InvalidAmountException;
import com.example.bank.exception.InsufficientFundsException;
import com.example.bank.model.Account;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void depositIncreasesBalance() {
        Account a = new Account("X1", "Valeriia", new BigDecimal("10.00"));
        a.deposit(new BigDecimal("2.50"));
        assertEquals(new BigDecimal("12.50"), a.getBalance());
    }

    @Test
    void withdrawDecreasesBalance() {
        Account a = new Account("X2", "Valeriia", new BigDecimal("10.00"));
        a.withdraw(new BigDecimal("3.00"));
        assertEquals(new BigDecimal("7.00"), a.getBalance());
    }

    @Test
    void cannotWithdrawMoreThanBalance() {
        Account a = new Account("X3", "Valeriia", new BigDecimal("5.00"));
        assertThrows(InsufficientFundsException.class, () -> a.withdraw(new BigDecimal("6.00")));
        assertEquals(new BigDecimal("5.00"), a.getBalance());
    }

    @Test
    void depositRejectsNonPositive() {
        Account a = new Account("X4", "Valeriia", new BigDecimal("5.00"));
        assertAll(
            () -> assertThrows(InvalidAmountException.class, () -> a.deposit(new BigDecimal("0.00"))),
            () -> assertThrows(InvalidAmountException.class, () -> a.deposit(new BigDecimal("-1.00")))
        );
    }
}
