import java.util.ArrayList;
import java.util.List;

public class Bank {

	private List<BankAccount> accounts = new ArrayList<>();

	public void createAccount(String accountType, String name) {
		accounts.add(new BankAccount(accountType, name));
	}

	public List<BankAccount> getAccounts() {
		return this.accounts;
	}

	public void deposit(int id, double amount) {
		if (amount > 0) {
			BankAccount currentAccount = this.getCurrentBankAccount(id, accounts);

			if (currentAccount != null) {
				currentAccount.setBalance(this.getOriginalBalance(currentAccount) + amount);
			}
		}
	}

//	
	public void withdraw(int id, double amount) {
		BankAccount currentAccount = this.getCurrentBankAccount(id, accounts);
		double orgBalance = this.getOriginalBalance(currentAccount);

		if (amount > 0  && amount < orgBalance) {
			currentAccount.setBalance(orgBalance - amount);
		} else if(amount > 0) {
			currentAccount.setBalance(0.00);
		}
	}

	private BankAccount getCurrentBankAccount(int id, List<BankAccount> accounts) {
		BankAccount currentAccount = null;
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountId() == id) {
				currentAccount = accounts.get(i);
			}
		}

		if (currentAccount != null) {
			return currentAccount;
		}

		return null;
	}

	private double getOriginalBalance(BankAccount bankAccount) {
		return bankAccount.getBalance();
	}
}
