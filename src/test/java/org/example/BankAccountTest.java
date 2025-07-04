package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Create a fresh account before each test
        account = new BankAccount(123456, "DeVinci", 1000.00);
    }

    @Test
    void testDepositValidAmount() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getAvailableBalance(), 0.001);
    }

    @Test
    void testDepositInvalidAmount() {
        account.deposit(-100.0);
        assertEquals(1000.0, account.getAvailableBalance(), 0.001); // Should not change
    }

    @Test
    void testWithdrawValidAmount() {
        account.withdraw(300.0);
        assertEquals(700.0, account.getAvailableBalance(), 0.001);
    }

    @Test
    void testWithdrawInvalidAmount() {
        account.withdraw(-50.0);
        assertEquals(1000.0, account.getAvailableBalance(), 0.001); // Should not change
    }

    @Test
    void testWithdrawExceedsBalance() {
        account.withdraw(1500.0);
        assertEquals(1000.0, account.getAvailableBalance(), 0.001); // Should not change
    }

    @Test
    void testGetAccountNumber() {
        assertEquals(123456, account.getAccountNumber());
    }

    @Test
    void testCreateAccountWithoutDeposit() {
        BankAccount newAccount = new BankAccount(654321, "Persephone", 0.0);
        assertEquals(0.0, newAccount.getAvailableBalance(), 0.001);
        assertEquals(654321, newAccount.getAccountNumber());
        assertEquals("Persephone", newAccount.getHolderName());
    }
}
