package command.impl.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.RequestDAO;
import DAO.impl.RequestDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
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
				request.setAttribute("cancel_message", "Request canceled!");
			} else {
				request.setAttribute("cancel_message", "Reviewed request can't be canceled!");
			}
			return new ForwardPage((String)request.getSession().getAttribute("current_page"));
		} catch (DaoException e) {
			logger.error("Error while cancel user request", e);
			request.setAttribute("error_message", "Error while cancel user request");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}

}
