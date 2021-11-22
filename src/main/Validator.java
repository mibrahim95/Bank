package main;

import java.util.Arrays;
import java.util.List;

public class Validator {

	private List<BankAccount> accounts;

	Validator(List<BankAccount> accounts) {
		this.accounts = accounts;

	}

	public boolean isValidCommand(final String command) {
		String[] commandsGiven = command.split(" ");
		String action = this.getFromArray(commandsGiven, 0);

		if (action.equalsIgnoreCase("create")) {
			String type = this.getFromArray(commandsGiven, 1);
			String name = this.getFromArray(commandsGiven, 2);
			if (!type.isEmpty() && !name.isEmpty()) {
				return this.isValidAccountType(type);
			}

		} else if (action.equalsIgnoreCase("deposit") || action.equalsIgnoreCase("withdraw")) {
			String amount = this.getFromArray(commandsGiven, 1);
			String id = this.getFromArray(commandsGiven, 2);
			if (!id.isEmpty() && !amount.isEmpty()) {
			return this.isValidAccountAndAmount(id, amount);
			}
		}
		return false;
	}

	public boolean isValidAccountType(final String type) {
		List<String> validAccounts = Arrays.asList("checking", "savings", "cd");
		return validAccounts.contains(type);
	}

	public boolean isValidAccountId(final int id) {
		return this.accounts.stream().anyMatch(account -> account.getId() == id);
	}

	public boolean isValidAmount(final double amount) {
		return amount > 0;

	}

	public boolean isValidAccountAndAmount(final String id, final String amount) {
		if (this.isValidAccountId(Integer.parseInt(id))) {
			return this.isValidAmount(Double.parseDouble(amount));
		}
		return false;
	}

	private String getFromArray(String[] array, int index) {
		try {
			return array[index];
		} catch (Exception e) {
			return " ";
		}

	}
}
