package command.impl.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.RequestDAO;
import DAO.impl.RequestDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import entity.Request;
import exception.DaoException;
import utils.CurrentPageExtractor;

public class GoToUserRequestListPageCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(GoToUserRequestListPageCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {

		int listPage = 1;
		int recordsPerPage = 8;
		if (request.getParameter("listPage") != null) {
			listPage = Integer.parseInt(request.getParameter("listPage"));
		}
		if (request.getParameter("recordsPerPage") != null) {
			recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
		}
		request.setAttribute("current_page", CurrentPageExtractor.extract(request));
		int userId = (Integer) request.getSession().getAttribute("user_id");
		RequestDAO requestDao = new RequestDaoImpl();
		try {
			List<Request> listOfRequests = requestDao.findAllByUserId(userId, recordsPerPage, listPage);
			request.setAttribute("listOfRequests", listOfRequests);
			int rows = requestDao.countAllByUserId(userId);
			int numberOfPages = (int) Math.ceil((double) rows / recordsPerPage);
			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("listPage", listPage);
			request.setAttribute("recordsPerPage", recordsPerPage);

		} catch (DaoException e) {
			logger.warn("Error while generate list of requests", e);
			request.setAttribute("dao_error_message",
					"Some problems with data base. List of requests must be there. Please refresh the page!");
		}
		return new ForwardPage(PagePath.USER_REQUEST_LIST);
	}

}
