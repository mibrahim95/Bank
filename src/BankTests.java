import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTests {
	private Bank testBank = new Bank();;

	@BeforeEach
	public void setUp() {
		testBank.createAccount("checking", "abc");
	}

	@Test
	public void test_createAccount_isValidChecking() {
		testBank.createAccount("checking", "xyz");
		assertFalse(testBank.getAccounts().isEmpty());
		assertEquals("xyz", testBank.getAccounts().get(1).getName());
		assertNotNull(testBank.getAccounts().get(1).getAccountId());
		assertEquals("checking", testBank.getAccounts().get(1).getAccountType());
		assertEquals(0, testBank.getAccounts().get(1).getBalance().intValue());
	}

	@Test
	public void test_createAccount_accountIdIsUnqiue() {
		testBank.createAccount("checking", "mojahed");
		testBank.createAccount("checking", "billyBob");
		assertNotEquals(testBank.getAccounts().get(1).getAccountId(), testBank.getAccounts().get(2).getAccountId());
	}
	
	
	@Test
	public void test_deposit_withValidAmount() {
		testBank.createAccount("checking", "mojahed");
		testBank.deposit(testBank.getAccounts().get(1).getAccountId(), 100);
		assertEquals(100, testBank.getAccounts().get(1).getBalance().intValue());
	}
	
	@Test
	public void test_deposit_withInvalidAmount() {
		testBank.createAccount("checking", "mojahed");
		testBank.deposit(testBank.getAccounts().get(1).getAccountId(), -100);
		assertEquals(0, testBank.getAccounts().get(1).getBalance().intValue());
	}
	
	@Test
	public void test_Withdraw_withValidAmountLessThanBalance() {
		testBank.createAccount("checking", "mojahed");
		testBank.deposit(testBank.getAccounts().get(1).getAccountId(), 100);
		testBank.withdraw(testBank.getAccounts().get(1).getAccountId(), 30);
		assertEquals(70, testBank.getAccounts().get(1).getBalance().intValue());
	}
	
	@Test
	public void test_Withdraw_withValidAmountGreaterThanBalance() {
		testBank.createAccount("checking", "mojahed");
		testBank.deposit(testBank.getAccounts().get(1).getAccountId(), 100);
		testBank.withdraw(testBank.getAccounts().get(1).getAccountId(), 110);
		assertEquals(0, testBank.getAccounts().get(1).getBalance().intValue());
	}
	
	@Test
	public void test_Withdraw_withInvalidAmount() {
		testBank.createAccount("checking", "mojahed");
		testBank.deposit(testBank.getAccounts().get(1).getAccountId(), 100);
		testBank.withdraw(testBank.getAccounts().get(1).getAccountId(), -20);
		assertEquals(100, testBank.getAccounts().get(1).getBalance().intValue());
	}

}
