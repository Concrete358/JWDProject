package command.impl.user;

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
import utils.CurrentPageExtractor;

public class GoToUserPageCommand implements ICommand {
private static final Logger logger = Logger.getLogger(GoToUserPageCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		request.setAttribute("current_page", CurrentPageExtractor.extract(request));
		int userId = (int)request.getSession().getAttribute("user_id");
		UserDAO userDao = new UserDaoImpl();
		try {
			Optional<User> userOptional = userDao.findUserById(userId);
			if(userOptional.isPresent()) {
				User user = userOptional.get();
				request.setAttribute("user", user);
			} else {
				logger.warn("USER INFO HAVE NOT BEEN FOUNDED!");
				request.setAttribute("error_message", "USER INFO HAVE NOT BEEN FOUNDED!");
				return new ForwardPage(PagePath.ERROR_404);
			}
			return new ForwardPage(PagePath.USER_USER_PAGE);
			} catch (DaoException e) {
			logger.warn("Something wrong with DB while searcing user by ID", e);
			request.setAttribute("error_message", "Error while searcing user by ID");
			return new ForwardPage(PagePath.ERROR_500);
			}	

}

}
