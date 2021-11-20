package main;

public class CommandHandler {

	private Validator validator = new Validator();

	public void processCommand(final String command) {
		validator.isValidCommands(command);
		
	}
	
}
