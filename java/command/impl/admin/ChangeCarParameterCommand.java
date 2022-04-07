package command.impl.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.CarDAO;
import DAO.impl.CarDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import command.page.RedirectPage;
import constants.PagePath;
import exception.DaoException;

public class ChangeCarParameterCommand implements ICommand{
	Logger logger = Logger.getLogger(ChangeCarParameterCommand.class);
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		String paramName = request.getParameter("param_name");
		int carId = Integer.parseInt(request.getParameter("car_id"));
		String paramValue = request.getParameter("param_value");
		CarDAO carDao = new CarDaoImpl();
		try {
			carDao.changeParameter(carId, paramName, paramValue);
			request.setAttribute("change_parameter_message", "DONE!");
			return new RedirectPage(request.getParameter("current_page"));
		} catch (DaoException e) {
			logger.error("Error while change parameter of car");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}

}
