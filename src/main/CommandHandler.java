package main;

public class CommandHandler {

	private Bank bank;
	private Validator validator;
	private CommandHistory commandHistory;
	private CommandObject commandObject = new CommandObject();


	public CommandHandler(final Bank bank, final CommandHistory commandHistory) {
		this.bank = bank;
		this.validator = new Validator(this.bank.getAccounts());
		this.commandHistory = commandHistory;
	}

	public boolean processCommand(final String command) {
		boolean isValidCommand = false;
		this.commandObject.setCommand(command);
		if (validator.isValidCommand(command)) {
			String[] splitCommands = command.split(" ");

			if (splitCommands[0].equalsIgnoreCase("create")) {
				isValidCommand = this.bank.createAccount(splitCommands[1], splitCommands[2]);
			} else if (splitCommands[0].equalsIgnoreCase("deposit")) {
				isValidCommand =  this.bank.deposit(Integer.parseInt(splitCommands[2]), Integer.parseInt(splitCommands[1]));
			} else if (splitCommands[0].equalsIgnoreCase("withdraw")) {
				isValidCommand = this.bank.withdraw(Integer.parseInt(splitCommands[2]), Integer.parseInt(splitCommands[1]));
			}
			this.commandObject.setValid(isValidCommand);
			this.commandHistory.getCommands().add(this.commandObject);
			return isValidCommand;
		}
		this.commandObject.setValid(false);
		this.commandHistory.getCommands().add(this.commandObject);
		return false;
	}
}
