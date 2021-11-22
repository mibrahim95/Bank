package main;

public class CommandHandler {

	private Bank bank;
	private Validator validator;
	private CommandHistory commandHistory = new CommandHistory();
	private CommandObject commandObject = new CommandObject();


	public CommandHandler(final Bank bank) {
		this.bank = bank;
		this.validator = new Validator(this.bank.getAccounts());
	}

	public boolean processCommand(final String command) {
		this.commandObject.setCommand(command);
		if (validator.isValidCommand(command)) {
			String[] splitCommands = command.split(" ");

			if (splitCommands[0].equalsIgnoreCase("create")) {
				this.bank.createAccount(splitCommands[1], splitCommands[2]);
			} else if (splitCommands[0].equalsIgnoreCase("deposit")) {
				this.bank.deposit(Integer.parseInt(splitCommands[2]), Integer.parseInt(splitCommands[1]));
			} else if (splitCommands[0].equalsIgnoreCase("withdraw")) {
				this.bank.withdraw(Integer.parseInt(splitCommands[2]), Integer.parseInt(splitCommands[1]));
			}
			this.commandObject.setValid(true);
			this.commandHistory.getCommands().add(this.commandObject);
			return true;
		}
		this.commandObject.setValid(false);
		this.commandHistory.getCommands().add(this.commandObject);
		return false;
	}
}
