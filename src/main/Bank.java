package main;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<BankAccount> accounts = new ArrayList<>();
	private Validator validator = new Validator(accounts);

	public void createAccount(final String accountType, final String name) {
		if (validator.isValidAccountType(accountType)) {
			accounts.add(new BankAccount(accountType, name));
		}
	}

	public List<BankAccount> getAccounts() {
		return this.accounts;
	}

	public void deposit(final int id, final double amount) {
		if (validator.isValidAmount(amount)) {
			BankAccount currentAccount = this.getAccountById(id, accounts);

			if (currentAccount != null) {
				currentAccount.setBalance(this.getOriginalBalance(currentAccount) + amount);
			}
		}
	}

//	
	public boolean withdraw(final int id, final double amount) {
		BankAccount currentAccount = this.getAccountById(id, accounts);

		if (currentAccount != null) {
			double orgBalance = this.getOriginalBalance(currentAccount);

			if (amount > 0 && amount < orgBalance) {
				currentAccount.setBalance(orgBalance - amount);
			} else if (amount > 0 && amount > orgBalance) { // left this way to avoid someone withdrawing -25$
				currentAccount.setBalance(0.00);
			}
			return true;
		}
		return false;
	}

	private BankAccount getAccountById(final int id, final List<BankAccount> accounts) {
		return accounts.stream().filter(account -> account.getId() == id).findFirst().orElse(null);

	}

	private double getOriginalBalance(final BankAccount bankAccount) {
		return bankAccount.getBalance();
	}
}
