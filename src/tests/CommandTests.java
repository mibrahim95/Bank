package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import main.CommandHandler;
import main.CommandHistory;
import main.Bank;

public class CommandTests {
	
	
	private Bank testBank =  new Bank();
	private CommandHistory commandHistory = new CommandHistory();
	private CommandHandler comhandle = new CommandHandler(testBank,commandHistory);
	
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
		assertTrue(testBank.createAccount("checking", "ahmad"));
		int tempId = testBank.getAccounts().get(0).getId();
		assertTrue(comhandle.processCommand("deposit 100 "+tempId));
		assertEquals(100, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_depositWithInvalidAmount() {
		assertTrue(testBank.createAccount("checking", "mojahed2"));
		int tempId = testBank.getAccounts().get(0).getId();
		assertFalse(comhandle.processCommand("deposit -100 "+tempId));
		assertEquals(0, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_depositWithInvalidAccount() {
		assertTrue(testBank.createAccount("checking", "mojahed2"));
		assertFalse(comhandle.processCommand("deposit 100 777777"));
		assertEquals(0, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_withdrawWithValidAmount() {
		assertTrue(testBank.createAccount("checking", "mojahed3"));
		int tempId = testBank.getAccounts().get(0).getId();
		assertTrue(testBank.deposit(tempId, 100));
		assertTrue(comhandle.processCommand("withdraw 30 "+tempId));
		assertEquals(70, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_withdrawWithValidAmountGreaterThanBalance() {
		assertTrue(testBank.createAccount("checking", "mojahed3"));
		int tempId = testBank.getAccounts().get(0).getId();
		assertTrue(testBank.deposit(tempId, 100));
		assertTrue(comhandle.processCommand("withdraw 110 "+tempId));
		assertEquals(0, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_withdrawWithInvalidAmount() {
		assertTrue(testBank.createAccount("checking", "mojahed3"));
		int tempId = testBank.getAccounts().get(0).getId();
		assertTrue(testBank.deposit(tempId, 100));
		assertFalse(comhandle.processCommand("withdraw -20 "+tempId));
		assertEquals(100, testBank.getAccounts().get(0).getBalance().intValue());
	}
	
	@Test
	public void test_processCommand_withdrawWithInvalidAccount() {
		assertTrue(testBank.createAccount("checking", "mojahed3"));
		assertTrue(testBank.deposit(testBank.getAccounts().get(0).getId(), 100));
		assertFalse(comhandle.processCommand("withdraw 30 777777"));
		assertEquals(100, testBank.getAccounts().get(0).getBalance().intValue());
	}
	@Test
	public void test_processCommand_withdrawWithInvalidAccountType() {
		assertTrue(testBank.createAccount("savings", "mojahed3"));
		int tempId = testBank.getAccounts().get(0).getId();
		assertTrue(testBank.deposit(tempId, 100));
		assertFalse(comhandle.processCommand("withdraw 30 "+tempId));
		assertEquals(100, testBank.getAccounts().get(0).getBalance().intValue());
	}
}
