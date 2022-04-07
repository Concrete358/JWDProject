package command.impl.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.RequestDAO;
import DAO.impl.RequestDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import command.page.RedirectPage;
import constants.PagePath;
import exception.DaoException;

public class RequestRejectCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(RequestRejectCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		if (request.getParameter("review_status").equals("true")) {
			request.getSession().setAttribute("approve_message", "approve_message_1.admin_page");
		} else {
		RequestDAO requestDao = new RequestDaoImpl();
		try {
			int id = Integer.parseInt(request.getParameter("request_id"));
			String reason = request.getParameter("reject_reason");
			requestDao.reject(id, reason);
			request.getSession().setAttribute("approve_message", "approve_message_4.admin_page");
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while while approving request and creating order", e);
			request.setAttribute("error_message", "admin_reject_request.error");
			return new ForwardPage(PagePath.ERROR_500);
			}
		}
		return new RedirectPage(request.getParameter("current_page"));
	}
}
