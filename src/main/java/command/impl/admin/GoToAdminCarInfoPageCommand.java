package command.impl.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.CarDAO;
import DAO.OrderDAO;
import DAO.impl.CarDaoImpl;
import DAO.impl.OrderDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import entity.Order;
import entity.car.Car;
import exception.DaoException;
import utils.CurrentPageExtractor;

public class GoToAdminCarInfoPageCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(GoToAdminCarInfoPageCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		request.getSession().setAttribute("current_page", CurrentPageExtractor.extract(request));	
		int carId = Integer.parseInt(request.getParameter("car_id"));
		CarDAO carDao = new CarDaoImpl();
		OrderDAO orderDao = new OrderDaoImpl();
		try {
			Optional<Car> carOptional = carDao.findById(carId);
			List<Order> orderList = orderDao.findActiveByCarId(carId);
			if (carOptional.isPresent()) {
				Car car = carOptional.get();
				request.setAttribute("car", car);
				request.setAttribute("order_list", orderList);
				return new ForwardPage(PagePath.ADMIN_CAR_INFO);
			} else {
				request.setAttribute("error_message", "CAR PAGE HAVE NOT BEEN FOUNDED!");
				return new ForwardPage(PagePath.ERROR_404);
			}
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while searcing car info", e);
			request.setAttribute("error_message", "Error while searcing car info");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}
}
