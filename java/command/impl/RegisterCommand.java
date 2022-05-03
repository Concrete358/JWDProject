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
import entity.UserFactory;
import exception.DaoException;
import utils.validator.RequestDataValidator;

public class RegisterCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(RegisterCommand.class);
	@Override
	public AbstractPage execute(HttpServletRequest request) {
			User user = UserFactory.getUserFromRequest(request);
			String password = request.getParameter("password");
			if(!RequestDataValidator.validateUserRegisterParameters(user) || !RequestDataValidator.validatePassword(password)) {
				request.getSession().setAttribute("RegisterErrorText", "error_text_invalid_parameters.common");
				return new RedirectPage(PagePath.REGISTER);
			}
			UserDAO userDao = new UserDaoImpl();
			try {
				Optional<User> userOptional = userDao.add(user, password);
				if(userOptional.isPresent()) {
					user = userOptional.get();
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					session.setAttribute("role", user.getRole().name());
					session.setAttribute("user_id", user.getId());
					session.setAttribute("user_name", user.getName());
					session.setAttribute("email", user.getEmail());
					return new RedirectPage(PagePath.INDEX);
				} else {
					request.setAttribute("RegisterErrorText", "email_taken_error.register");
					return new ForwardPage(PagePath.REGISTER);
				}
			} catch (DaoException e) {
				logger.warn("Something wrong with DB while register new user", e);
				request.setAttribute("error_message", "user_create.error");
				return new ForwardPage(PagePath.ERROR_500);
			}
		}
}
