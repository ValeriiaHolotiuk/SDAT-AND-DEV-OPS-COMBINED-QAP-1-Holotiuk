package com.example.bank;

import com.example.bank.service.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankTransferTest {

    private Bank bank;

    @BeforeEach
    void setup() {
        bank = new Bank();
        bank.createAccount("A1", "Main", new BigDecimal("100.00"));
        bank.createAccount("A2", "Savings", new BigDecimal("0.00"));
    }

    @Test
    void transferMovesMoneyBetweenAccounts() {
        bank.transfer("A1", "A2", new BigDecimal("40.00"));
        assertEquals(new BigDecimal("60.00"), bank.find("A1").getBalance());
        assertEquals(new BigDecimal("40.00"), bank.find("A2").getBalance());
    }
}
