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
					request.setAttribute("request_edit_message",
							"Request edited");
				} else {
					request.setAttribute("request_edit_message", "Reviewed requests can't be edited. Changes can't be done.");
				}
				return new ForwardPage((String)request.getSession().getAttribute("current_page"));
			} catch (DaoException e) {
				logger.warn("Something wrong with DB while while edit request", e);
				request.setAttribute("error_message", "Error while edit request.");
				return new ForwardPage(PagePath.ERROR_500);
			}
		} else {
			request.setAttribute("request_edit_message",
					"Incorrect date! Please change it!<p>1) Start date can't be today or earlier.<p>2) Finish date must be one day after start date. ");
			return new ForwardPage((String)request.getSession().getAttribute("current_page"));
		}
	}
}
