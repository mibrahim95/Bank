package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import main.CommandHandler;
import main.Bank;

public class CommandTests {
	
	
	private Bank testBank =  new Bank();
	private CommandHandler comhandle = new CommandHandler(testBank);
	
	@Test
	public void test_processCommand_withInvalidCommand() {
		assertFalse(comhandle.processCommand("create"));
	}
	
	@Test
	public void test_processCommand_createAccountWithValidAccountType() {
		assertTrue(comhandle.processCommand("create checking mojahed"));
		assertEquals(1, testBank.getAccounts().size());
	}
	@Test
	public void test_processCommand_createAccountWithInvalidAccountType() {
		assertFalse(comhandle.processCommand("create lhglhg mojahed"));
		assertEquals(0, testBank.getAccounts().size());
	}
	
	@Test
	public void test_processCommand_depositWithValidAmount() {
		testBank.createAccount("checking", "ahmad");
		int tempId = testBank.getAccounts().get(0).getId();
		assertTrue(comhandle.processCommand("deposit 100 "+tempId));
		assertEquals(100, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_depositWithInvalidAmount() {
		testBank.createAccount("checking", "mojahed2");
		int tempId = testBank.getAccounts().get(0).getId();
		assertFalse(comhandle.processCommand("deposit -100 "+tempId));
		assertEquals(0, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_depositWithInvalidAccount() {
		testBank.createAccount("checking", "mojahed2");
		assertFalse(comhandle.processCommand("deposit 100 777777"));
		assertEquals(0, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_withdrawWithValidAmount() {
		testBank.createAccount("checking", "mojahed3");
		int tempId = testBank.getAccounts().get(0).getId();
		testBank.deposit(tempId, 100);
		assertTrue(comhandle.processCommand("withdraw 30 "+tempId));
		assertEquals(70, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_withdrawWithValidAmountGreaterThanBalance() {
		testBank.createAccount("checking", "mojahed3");
		int tempId = testBank.getAccounts().get(0).getId();
		testBank.deposit(tempId, 100);
		assertTrue(comhandle.processCommand("withdraw 110 "+tempId));
		assertEquals(0, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_withdrawWithInvalidAmount() {
		testBank.createAccount("checking", "mojahed3");
		int tempId = testBank.getAccounts().get(0).getId();
		testBank.deposit(tempId, 100);
		assertFalse(comhandle.processCommand("withdraw -20 "+tempId));
		assertEquals(100, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_withdrawWithInvalidAccount() {
		testBank.createAccount("checking", "mojahed3");
		testBank.deposit(testBank.getAccounts().get(0).getId(), 100);
		assertFalse(comhandle.processCommand("withdraw 30 777777"));
		assertEquals(100, testBank.getAccounts().get(0).getBalance().intValue());
	}
}
