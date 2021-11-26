package main;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

	private List<Object> commands = new ArrayList<>();

	public List<Object> getCommands() {
		return this.commands;
	}

	public void setCommands(List<Object> commands) {
		this.commands = commands;
	}
}
