package command.impl.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.UserDAO;
import DAO.impl.UserDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
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
				request.setAttribute("change_password_message", "PASSWORD CHANGED");
			} else {
				request.setAttribute("change_password_message", "INCORRECT OLD PASSWORD");
			}
			return new ForwardPage((String) request.getSession().getAttribute("current_page"));
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while changing password", e);
			request.setAttribute("error_message", "Error while changing password");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}
}
