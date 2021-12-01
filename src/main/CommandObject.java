package main;

public class CommandObject {

	private String command;
	private boolean valid;

	public String getCommand() {
		return this.command;
	}

	public void setCommand(final String command) {
		this.command = command;
	}

	public boolean isValid() {
		return this.valid;
	}

	public void setValid(final boolean valid) {
		this.valid = valid;
	}
}
