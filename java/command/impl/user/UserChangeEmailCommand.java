package command.impl.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.UserDAO;
import DAO.impl.UserDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import command.page.RedirectPage;
import constants.PagePath;
import exception.DaoException;

public class UserChangeEmailCommand implements ICommand {
	private final static Logger logger = Logger.getLogger(UserChangeEmailCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		String newEmail = request.getParameter("new_email");
		int userId = (int) request.getSession().getAttribute("user_id");
		UserDAO userDao = new UserDaoImpl();
		try {
			if (userDao.changeEmail(userId, newEmail)) {
				request.getSession().setAttribute("change_email_message", "change_email_message_1.user_page");
			} else {
				request.getSession().setAttribute("change_email_message", "change_email_message_2.user_page");
			}
			return new RedirectPage(request.getParameter("current_page"));
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while user " + userId + "change email", e);
			request.setAttribute("error_message", "user_change_email.error");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}
}
