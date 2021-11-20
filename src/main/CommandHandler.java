package main;

public class CommandHandler {

	private Bank bank;
	private Validator validator;

	public CommandHandler(final Bank bank) {
		this.bank = bank;
		this.validator = new Validator(this.bank);
	}

	public boolean processCommand(final String command) {
		try {
			if (validator.isValidCommand(command)) {
				String[] splitCommands = command.split(" ");

				if (splitCommands[0].equalsIgnoreCase("create")) {
					this.bank.createAccount(splitCommands[1], splitCommands[2]);
				} else if (splitCommands[0].equalsIgnoreCase("deposit")) {
					this.bank.deposit(Integer.parseInt(splitCommands[2]), Integer.parseInt(splitCommands[1]));
				} else if (splitCommands[0].equalsIgnoreCase("withdraw")) {
					this.bank.withdraw(Integer.parseInt(splitCommands[2]), Integer.parseInt(splitCommands[1]));
				}
				return true;
				// testing the necessity of breaking out specific exceptions. Usually I just
				// catch Exception and display
				// that something went wrong. For the purpose of learning I am making sure to
				// grab the possible cases
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Your command is missing paramters!");
		} catch (NullPointerException e) {
			System.out.println("Your command is empty!");
		} catch (Exception e) {
			System.out.println("Theres an issue with your command");
		}
		return false;
	}
}
