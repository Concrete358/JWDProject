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

public class UserChangePasswordCommand implements ICommand {
	private final static Logger logger = Logger.getLogger(UserChangePasswordCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		String oldPass = request.getParameter("old_password");
		String newPass = request.getParameter("new_password");
		int userId = (int) request.getSession().getAttribute("user_id");
		UserDAO userDao = new UserDaoImpl();
		try {
			if (userDao.changePassword(userId, oldPass, newPass)) {
				request.getSession().setAttribute("change_password_message", "change_password_message_1.user_page");
			} else {
				request.getSession().setAttribute("change_password_message", "change_password_message_2.user_page");
			}
			return new RedirectPage(request.getParameter("current_page"));
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while changing password", e);
			request.setAttribute("error_message", "user_change_password.error");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}
}
