package command.impl.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.RequestDAO;
import DAO.impl.RequestDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import exception.DaoException;

public class RequestRejectCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(RequestRejectCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		if (request.getParameter("review_status").equals("true")) {
			request.setAttribute("approve_message", "This request is already reviewed!");
		} else {
		RequestDAO requestDao = new RequestDaoImpl();
		try {
			int id = Integer.parseInt(request.getParameter("request_id"));
			String reason = request.getParameter("reject_reason");
			requestDao.reject(id, reason);
			request.setAttribute("approve_message", "Request rejected!");
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while while approving request and creating order", e);
			request.setAttribute("error_message", "Error while reject request");
			return new ForwardPage(PagePath.ERROR_500);
			}
		}
		return new ForwardPage((String) request.getSession().getAttribute("current_page"));
	}
}
