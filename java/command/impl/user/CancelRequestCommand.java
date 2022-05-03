package command.impl.user;

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

public class CancelRequestCommand implements ICommand {
	private final static Logger logger = Logger.getLogger(CancelRequestCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		int requestId = Integer.parseInt(request.getParameter("request_id"));
		RequestDAO reqDao = new RequestDaoImpl();
		try {
			if(reqDao.deleteById(requestId)) {
				request.getSession().setAttribute("cancel_message", "cancel_message_text1.user_requests");
			} else {
				request.getSession().setAttribute("cancel_message", "cancel_message_text2.user_requests");
			}
			return new RedirectPage(request.getParameter("current_page"));
		} catch (DaoException e) {
			logger.error("Error while cancel user request", e);
			request.setAttribute("error_message", "cancel_request.error");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}

}
