package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Bank;

public class BankTests {
	private Bank testBank = new Bank();

	@BeforeEach
	public void setUp() {
		testBank.createAccount("checking", "mojahed");
	}

	@Test
	public void test_createAccount_invalidAccountType() {
		assertFalse(testBank.createAccount("nonsense", "xyz"));
		assertEquals(1, testBank.getAccounts().size());
	}

	@Test
	public void test_createAccount_isValidChecking() {
		assertFalse(testBank.getAccounts().isEmpty());
		assertEquals("mojahed", testBank.getAccounts().get(0).getName());
		assertNotNull(testBank.getAccounts().get(0).getId());
		assertEquals("checking", testBank.getAccounts().get(0).getType());
		assertEquals(0, testBank.getAccounts().get(0).getBalance().intValue());
	}

	@Test
	public void test_createAccount_accountIdIsUnqiue() {
		assertTrue(testBank.createAccount("checking", "xyz"));
		assertNotEquals(testBank.getAccounts().get(0).getId(), testBank.getAccounts().get(1).getId());
	}

	@Test
	public void test_deposit_withValidAmount() {
		assertTrue(testBank.deposit(testBank.getAccounts().get(0).getId(), 100));
		assertEquals(100, testBank.getAccounts().get(0).getBalance().intValue());
	}

	@Test
	public void test_deposit_withInvalidAmount() {
		assertFalse(testBank.deposit(testBank.getAccounts().get(0).getId(), -100));
		assertEquals(0, testBank.getAccounts().get(0).getBalance().intValue());
	}

	@Test
	public void test_deposit_withInvalidAccount() {
		assertFalse(testBank.deposit(77777, 100));
		assertEquals(0, testBank.getAccounts().get(0).getBalance().intValue());
	}

	@Test
	public void test_Withdraw_withValidAmountLessThanBalance() {
		assertTrue(testBank.createAccount("checking", "mojahed"));
		assertTrue(testBank.deposit(testBank.getAccounts().get(0).getId(), 100));
		assertTrue(testBank.withdraw(testBank.getAccounts().get(0).getId(), 30));
		assertEquals(70, testBank.getAccounts().get(0).getBalance().intValue());
	}

	@Test
	public void test_Withdraw_withValidAmountGreaterThanBalance() {
		assertTrue(testBank.createAccount("checking", "mojahed"));
		assertTrue(testBank.deposit(testBank.getAccounts().get(0).getId(), 100));
		assertTrue(testBank.withdraw(testBank.getAccounts().get(0).getId(), 110));
		assertEquals(0, testBank.getAccounts().get(0).getBalance().intValue());
	}

	@Test
	public void test_Withdraw_withInvalidAmount() {
		assertTrue(testBank.deposit(testBank.getAccounts().get(0).getId(), 100));
		assertTrue(testBank.withdraw(testBank.getAccounts().get(0).getId(), -20));
		assertEquals(100, testBank.getAccounts().get(0).getBalance().intValue());
	}

	@Test
	public void test_Withdraw_withInvalidAccount() {
		assertTrue(testBank.deposit(testBank.getAccounts().get(0).getId(), 100));
		assertFalse(testBank.withdraw(77777, 30));
		assertEquals(100, testBank.getAccounts().get(0).getBalance().intValue());
	}

	@Test
	public void test_Withdraw_withInvalidAccountType() {
		assertTrue(testBank.createAccount("savings", "mojahed"));
		assertTrue(testBank.deposit(testBank.getAccounts().get(1).getId(), 100));
		assertFalse(testBank.withdraw(testBank.getAccounts().get(1).getId(), 30));
		assertEquals(100, testBank.getAccounts().get(1).getBalance().intValue());
	}

}
