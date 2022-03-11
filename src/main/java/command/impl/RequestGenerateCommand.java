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
					request.getSession().setAttribute("rent_request", reqOptional.get());
				} else {
					request.setAttribute("RequestErrorText",
							"You have created the same request already! We do not duplicate it.");
				}
				return new ForwardPage((String)request.getSession().getAttribute("current_page"));
			} catch (DaoException e) {
				logger.warn("Something wrong with DB while while creating request", e);
				request.setAttribute("error_message", "Error while creating request for car rent.");
				return new ForwardPage(PagePath.ERROR_500);
			}
		} else {
			request.setAttribute("RequestErrorText",
					"Incorrect date! Please change it!<p>1) Start date can't be today or earlier.<p>2) Finish date must be one day after start date. ");
			return new ForwardPage((String)request.getSession().getAttribute("current_page"));
		}
	}
}
