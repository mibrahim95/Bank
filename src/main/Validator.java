package main;

import java.util.Arrays;
import java.util.List;

public class Validator {

	private Bank bankToValidate;

	Validator(final Bank bankToValidate) {
		this.bankToValidate = bankToValidate;
	}

	public boolean isValidCommand(final String command) {
		try {
			String[] commandsGiven = command.split(" ");
			if (commandsGiven[0].equalsIgnoreCase("create")) {
				return this.isValidAccountType(commandsGiven[1]);

			} else if (commandsGiven[0].equalsIgnoreCase("deposit") || commandsGiven[0].equalsIgnoreCase("withdraw")) {
				return this.isValidAccountAndAmount(commandsGiven);

			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Cannot validate an incomplete command");
		} catch(Exception e) {
			System.out.println("Failed validation fix your command");
		}
		return false;
	}

	private boolean isValidAccountType(final String type) {
		List<String> validAccounts = Arrays.asList("checking", "savings", "cd");
		return validAccounts.contains(type);
	}

	private boolean isValidAccountId(final int id) {
		return this.bankToValidate.getAccounts().stream().anyMatch(account -> account.getId() == id);
	}

	private boolean isValidAmount(final double amount) {
		return amount > 0;

	}

	private boolean isValidAccountAndAmount(final String[] commands) {
		try {
			if (this.isValidAccountId(Integer.parseInt(commands[2]))) {
				return this.isValidAmount(Double.parseDouble(commands[1]));
			}
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid integer ID and Double amount");
		} catch (Exception e) {
			System.out.println("Something else went wrong");
		}
		return false;

	}

}
