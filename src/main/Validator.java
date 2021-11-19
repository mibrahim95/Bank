package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {

	private List<BankAccount> accounts = new ArrayList<>();

	public boolean checkValidAccountType(final String type) {
		List<String> validAccounts = Arrays.asList("checking", "savings", "cd");

		if (validAccounts.contains(type)) {
			return true;
		}
		return false;
	}
	
	public boolean checkValidAccountId(final String id) {
		if(accounts.contains(id)) {
			return true;
		}
		return false;
		
	}
	
	public boolean checkValidAmount(final String amount) {
		if(Integer.parseInt(amount) > 0) {
			return true;
		}
		return false;
		
	}

	public boolean validateCommandsGiven(final String command) {
		String[] commandsGiven = new String[0];

		if (command != "") {
			commandsGiven = command.split(" ");
			if (commandsGiven[0] == "create") {
				return createCheck(commandsGiven);

			} else if (commandsGiven[0] == "deposit") {
				return depositeCheck(commandsGiven);

			} else if (commandsGiven[0] == "withdraw") {
				return withdrawCheck(commandsGiven);

			}
		}
		return false;
	}

	public boolean createCheck(final String[] commands) {
		// only checking if second command is the account type
		if(this.checkValidAccountType(commands[1])) {
			return true;
		}
		return false;

	}

	public boolean depositeCheck(final String[] commands) {
		if(this.checkValidAccountId(commands[1])) {
			if(this.checkValidAmount(commands[2])) {
				return true;
			}
		}
		return false;

	}

	public boolean withdrawCheck(final String[] commands) {
		if(this.checkValidAccountId(commands[1])) {
			if(this.checkValidAmount(commands[2])) {
				return true;
			}
		}
		return false;
	}
}
