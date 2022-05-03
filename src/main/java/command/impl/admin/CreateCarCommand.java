package command.impl.admin;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.CarDAO;
import DAO.impl.CarDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import command.page.RedirectPage;
import constants.PagePath;
import entity.car.Car;
import entity.car.CarFactory;
import exception.DaoException;

public class CreateCarCommand implements ICommand {
	Logger logger = Logger.getLogger(CreateCarCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		Car car = CarFactory.getCarFromRequest(request);
		CarDAO carDao = new CarDaoImpl();
		try {
			Optional<Car> carOptional = carDao.add(car);
			if(carOptional.isEmpty()) {
				request.getSession().setAttribute("result_message", "Incorrect data. VIN or car number plate is already used");
			} else {
				request.getSession().setAttribute("result_message", "Operation completed");
			}
			return new RedirectPage(request.getParameter("current_page"));
		} catch (DaoException e) {
			logger.warn("DaoException while adding new car", e);
			request.setAttribute("error_message", "Error while create new car");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}

}
