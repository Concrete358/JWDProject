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
import command.page.RedirectPage;
import constants.PagePath;
import entity.User;
import exception.DaoException;
import utils.validator.RequestDataValidator;


public class LoginCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(LoginCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		String email = request.getParameter("email").toLowerCase();
		String password = request.getParameter("password");
		if(!RequestDataValidator.validateEmail(email)) {
			request.getSession().setAttribute("loginErrorText", "error_text_invalid_email.common");
			return new RedirectPage(PagePath.LOGIN);
		}
		if(!RequestDataValidator.validatePassword(password)) {
			logger.warn(password);
			request.getSession().setAttribute("loginErrorText", "error_text_invalid_password.common");
			return new RedirectPage(PagePath.LOGIN);
		}
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
				return new RedirectPage(PagePath.INDEX);
			} else {
				request.getSession().setAttribute("loginErrorText", "error_text.login");
				return new RedirectPage(PagePath.LOGIN);
			}
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while login", e);
			request.setAttribute("error_message", "Error while login");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}

}
