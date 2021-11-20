package main;

import java.util.Arrays;
import java.util.List;

public class Validator {

	private Bank bankToValidate = new Bank();

	public boolean isValidCommands(final String command) {
		String[] commandsGiven = command.split(" ");

		if (commandsGiven[0].equalsIgnoreCase("create")) {
			return this.isValidAccountType(commandsGiven[1]);

		} else if (commandsGiven[0].equalsIgnoreCase("deposit")) {
			return this.isValidAccountAndAmount(commandsGiven);

		} else if (commandsGiven[0].equalsIgnoreCase("withdraw")) {
			return this.isValidAccountAndAmount(commandsGiven);

		}
		return false;
	}

	private boolean isValidAccountType(final String type) {
		List<String> validAccounts = Arrays.asList("checking", "savings", "cd");
		return validAccounts.contains(type);
	}

	private boolean isValidAccountId(final int id) {
		return bankToValidate.getAccounts().stream().anyMatch(account -> account.getId() == id);
	}

	private boolean isValidAmount(final double amount) {
		return amount > 0;

	}

	private boolean isValidAccountAndAmount(final String[] commands) {
		if (this.isValidAccountId(Integer.parseInt(commands[1]))) {
			return this.isValidAmount(Double.parseDouble(commands[2]));
		}
		return false;

	}

}
