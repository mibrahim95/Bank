package main;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<BankAccount> accounts = new ArrayList<>();
	private Validator validator = new Validator();

	public void createAccount(final String accountType, final String name) {
		if(validator.checkValidAccountType(accountType)) {
			accounts.add(new BankAccount(accountType, name));
		}
	}

	public List<BankAccount> getAccounts() {
		return this.accounts;
	}

	public void deposit(final int id, final double amount) {
		if (amount > 0) {
			BankAccount currentAccount = this.getCurrentBankAccount(id, accounts);

			if (currentAccount != null) {
				currentAccount.setBalance(this.getOriginalBalance(currentAccount) + amount);
			}
		}
	}

//	
	public void withdraw(final int id, final double amount) {
		BankAccount currentAccount = this.getCurrentBankAccount(id, accounts);

		if (currentAccount != null) {
			double orgBalance = this.getOriginalBalance(currentAccount);

			if (amount > 0 && amount < orgBalance) {
				currentAccount.setBalance(orgBalance - amount);
			} else if (amount > 0 && amount > orgBalance) { // left this way to avoid someone withdrawing -25$
				currentAccount.setBalance(0.00);
			}
		}
	}

	private BankAccount getCurrentBankAccount(final int id, final List<BankAccount> accounts) {
		BankAccount currentAccount = null;
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getId() == id) {
				currentAccount = accounts.get(i);
			}
		}
		return currentAccount;
	}

	private double getOriginalBalance(final BankAccount bankAccount) {
		return bankAccount.getBalance();
	}
}
