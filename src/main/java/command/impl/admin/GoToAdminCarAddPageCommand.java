package command.impl.admin;

import javax.servlet.http.HttpServletRequest;

import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;

public class GoToAdminCarAddPageCommand implements ICommand {

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		return new ForwardPage(PagePath.ADMIN_CAR_ADD);
	}

}
