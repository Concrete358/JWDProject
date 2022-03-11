package command.impl.goToCommands;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.UserDAO;
import DAO.impl.UserDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import entity.User;
import exception.DaoException;

public class GoToUserInfoPageCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(GoToUserInfoPageCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("user_id"));
		UserDAO userDao = new UserDaoImpl();
		try {
			Optional<User> userOptional = userDao.findUserById(userId);
			if(userOptional.isPresent()) {
				User user = userOptional.get();
				request.setAttribute("user", user);
				return new ForwardPage(PagePath.ADMIN_USER_INFO);
			} else {
				request.setAttribute("error_message", "USER HAVE NOT BEEN FOUNDED!");
				return new ForwardPage(PagePath.ADMIN_USER_INFO);
			}
			} catch (DaoException e) {
			logger.warn("Something wrong with DB while searcing user by ID", e);
			request.setAttribute("error_message", "Error while searcing user by ID");
			return new ForwardPage(PagePath.ERROR_500);
			}	

}
}
