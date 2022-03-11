package command;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
	
	public ICommand defineCommand(HttpServletRequest request) {
		ICommand currentCommand = null;
		String action = request.getParameter("command");
		try {
		currentCommand = CommandEnum.valueOf(action.toUpperCase()).getCommand();
		} catch (IllegalArgumentException exc) {
			request.setAttribute("incorrect_command_name", action);
		}
		return currentCommand;
	}
}
