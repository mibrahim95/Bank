package main;

public class CommandHandler {

	private Bank bank;
	private Validator validator;

	public CommandHandler(final Bank bank) {
		this.bank = bank;
		this.validator = new Validator(this.bank);
	}


	public void processCommand(final String command) {
		if (validator.isValidCommand(command)) {
			String[] splitCommands = command.split(" ");
			
			if (splitCommands[0].equalsIgnoreCase("create")) {
				this.bank.createAccount(splitCommands[1], splitCommands[2]);
			} else if (splitCommands[0].equalsIgnoreCase("deposit")) {
				this.bank.deposit(Integer.parseInt(splitCommands[2]), Integer.parseInt(splitCommands[1]));
			} else if (splitCommands[0].equalsIgnoreCase("withdraw")) {
				this.bank.withdraw(Integer.parseInt(splitCommands[2]), Integer.parseInt(splitCommands[1]));
			}

		}
	}
}
