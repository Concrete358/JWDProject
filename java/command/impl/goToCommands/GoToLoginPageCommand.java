package command.impl.goToCommands;

import javax.servlet.http.HttpServletRequest;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;

public class GoToLoginPageCommand implements ICommand {
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		return new ForwardPage(PagePath.LOGIN);
	}

}
