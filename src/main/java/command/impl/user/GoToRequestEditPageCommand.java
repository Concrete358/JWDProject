package command.impl.user;

import java.util.Optional;

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

public class GoToRequestEditPageCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(GoToRequestEditPageCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
			request.setAttribute("current_page", CurrentPageExtractor.extract(request));
			int requestId = Integer.parseInt(request.getParameter("request_id"));
			RequestDAO requestDao = new RequestDaoImpl();
		try {Optional<Request> requestOptional = requestDao.findById(requestId);
			if(requestOptional.isPresent()) {
			request.setAttribute("request", requestOptional.get());
			return new ForwardPage(PagePath.USER_REQUEST_EDIT);
			} else {
				request.setAttribute("error_message", "REQUEST " + requestId + " NOT FOUNDED" );
				return new ForwardPage(PagePath.ERROR_404);				
			}
		} catch(DaoException e) {
		logger.warn("Error while go to request edit page", e);
		request.setAttribute("error_message", "Error while go to request edit page");
		return new ForwardPage(PagePath.ERROR_500);
		}
	}
}
