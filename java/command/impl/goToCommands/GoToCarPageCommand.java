package command.impl.goToCommands;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import DAO.CarDAO;
import DAO.impl.CarDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import entity.car.Car;
import exception.DaoException;
import utils.CurrentPageExtractor;

public class GoToCarPageCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(GoToCarPageCommand.class);
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		request.setAttribute("current_page", CurrentPageExtractor.extract(request));
		int car_id = Integer.parseInt(request.getParameter("id"));
		CarDAO carDao = new CarDaoImpl();
		try {
			Optional<Car> car = carDao.findById(car_id);
			if(car.isPresent()) {
				request.setAttribute("car", car.get());
				return new ForwardPage(PagePath.CAR);
			} else {
				request.setAttribute("error_message", "Car with ID = " + car_id + " is not founded");
				return new ForwardPage(PagePath.ERROR_404);
			}
		} catch(DaoException e) {
			logger.error(e);
			request.setAttribute("error_message", "Error while go to car page");
			return new ForwardPage(PagePath.ERROR_500);
			}
		}
}
