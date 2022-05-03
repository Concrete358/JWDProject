package command.impl;

import javax.servlet.http.HttpServletRequest;

import command.ICommand;
import command.page.AbstractPage;
import command.page.RedirectPage;
import constants.PagePath;

public class SetLocaleCommand implements ICommand {

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		request.getSession().setAttribute("locale", request.getParameter("language"));
		return new RedirectPage(PagePath.INDEX);
	}

}
