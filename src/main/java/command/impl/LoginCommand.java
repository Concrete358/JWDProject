package command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import DAO.UserDAO;
import DAO.impl.UserDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import entity.User;
import exception.DaoException;


public class LoginCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(LoginCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDAO userDao = new UserDaoImpl();
		try {
			Optional<User> userOptional = userDao.findUserByEmailandPassword(email, password);
			if(userOptional.isPresent()) {
				User user = userOptional.get();
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setAttribute("role", user.getRole().name());
				session.setAttribute("user_id", user.getId());
				session.setAttribute("user_name", user.getName());
				session.setAttribute("email", user.getEmail());
				return new ForwardPage(PagePath.INDEX);
			} else {
				request.setAttribute("loginErrorText", "INCORRECT EMAIL OR PASSWORD");
				return new ForwardPage(PagePath.LOGIN);
			}
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while login", e);
			request.setAttribute("error_message", "Error while login");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}

}
