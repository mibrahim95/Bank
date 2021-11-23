package main;

import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<BankAccount> accounts = new ArrayList<>();
	private Validator validator = new Validator(accounts);

	public boolean createAccount(final String accountType, final String name) {
		if (validator.isValidAccountType(accountType)) {
			accounts.add(new BankAccount(accountType, name));
			return true;
		}
		return false;
	}

	public List<BankAccount> getAccounts() {
		return this.accounts;
	}

	public boolean deposit(final int id, final double amount) {
		if (validator.isValidAmount(amount)) {
			BankAccount currentAccount = this.getAccountById(id, accounts);

			if (currentAccount != null) {
				currentAccount.setBalance(currentAccount.getBalance() + amount);
				return true;
			}
		}
		return false;
	}

	public boolean withdraw(final int id, final double amount) {
		BankAccount currentAccount = this.getAccountById(id, accounts);

		if (currentAccount != null && currentAccount.getType() == "checking") {

			double orgBalance = currentAccount.getBalance();

			if (amount > 0 && amount < orgBalance) {
				currentAccount.setBalance(orgBalance - amount);
			} else if (amount > 0 && amount > orgBalance) { // left this way to avoid someone withdrawing -25$
				currentAccount.setBalance(0.00);
			}
			return true;
		}
		return false;
	}

	public BankAccount getAccountById(final int id, final List<BankAccount> accounts) {
		return accounts.stream().filter(account -> account.getId() == id).findFirst().orElse(null);

	}
}
