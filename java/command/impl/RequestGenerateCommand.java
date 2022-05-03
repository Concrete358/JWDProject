package command.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.RequestDAO;
import DAO.impl.RequestDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import command.page.RedirectPage;
import constants.PagePath;
import entity.Request;
import entity.RequestFactory;

import exception.DaoException;
import utils.validator.RequestDataValidator;

public class RequestGenerateCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(RequestGenerateCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		String startDateStr = request.getParameter("rent_start_date");
		LocalDate startDate = Date.valueOf(startDateStr).toLocalDate();

		String finishDateStr = request.getParameter("rent_finish_date");
		LocalDate finishDate = Date.valueOf(finishDateStr).toLocalDate();

		if (RequestDataValidator.validateRentPeriod(startDate, finishDate)) {
			Request rentRequest = RequestFactory.getRequestFromFactory(request);
			RequestDAO reqDao = new RequestDaoImpl();
			try {
				Optional<Request> reqOptional = reqDao.add(rentRequest);
				if (reqOptional.isPresent()) {
					request.getSession().setAttribute("RequestMessage", 
							"request_message_1");
				} else {
					request.getSession().setAttribute("RequestMessage",
							"request_message_2");
				}
				return new RedirectPage(request.getParameter("current_page"));
			} catch (DaoException e) {
				logger.warn("Something wrong with DB while while creating request", e);
				request.setAttribute("error_message", "request_generate.error");
				return new ForwardPage(PagePath.ERROR_500);
			}
		} else {
			request.getSession().setAttribute("RequestMessage",
					"request_message_3");
			return new RedirectPage(request.getParameter("current_page"));
		}
	}
}
