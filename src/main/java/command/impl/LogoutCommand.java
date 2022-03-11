package command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;

public class LogoutCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(LogoutCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		request.getSession().invalidate();
		logger.debug("session invalidated");
		return new ForwardPage(PagePath.INDEX);
	}

}
