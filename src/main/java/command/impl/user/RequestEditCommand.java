package command.impl.user;

import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import DAO.RequestDAO;
import DAO.impl.RequestDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import command.page.RedirectPage;
import constants.PagePath;
import exception.DaoException;
import utils.validator.RequestDataValidator;

public class RequestEditCommand implements ICommand{
	Logger logger = LogManager.getLogger(RequestEditCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		int requestId = Integer.parseInt(request.getParameter("request_id"));
		Date stDate = Date.valueOf(request.getParameter("rent_start_date"));
		Date fnDate = Date.valueOf(request.getParameter("rent_finish_date"));
		LocalDate startDate = stDate.toLocalDate();
		LocalDate finishDate = fnDate.toLocalDate();
		if (RequestDataValidator.validateRentPeriod(startDate, finishDate)) {
			RequestDAO reqDao = new RequestDaoImpl();
			try {
				if (reqDao.changeDates(requestId, stDate, fnDate)) {
					request.setAttribute("request_edit_message", "edit_message_1.user_request_edit");
				} else {
					request.getSession().setAttribute("request_edit_message", "edit_message_2.user_request_edit");
				}
				return new RedirectPage(request.getParameter("current_page"));
			} catch (DaoException e) {
				logger.warn("Something wrong with DB while while edit request", e);
				request.setAttribute("error_message", "request_edit.error");
				return new ForwardPage(PagePath.ERROR_500);
			}
		} else {
			request.getSession().setAttribute("request_edit_message", "request_message_3");
			return new RedirectPage(request.getParameter("current_page"));
		}
	}
}
