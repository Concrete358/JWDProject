package command.impl.goToCommands;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import DAO.CarDAO;
import DAO.impl.CarDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import entity.car.Car;
import exception.DaoException;
import utils.CurrentPageExtractor;

public class GoToMainPageCommand implements ICommand {

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		try {
			int listPage = 1;
			int recordsPerPage = 3;
			request.getSession().setAttribute("current_page", CurrentPageExtractor.extract(request));
			if (request.getParameter("listPage") != null) {
				listPage = Integer.parseInt(request.getParameter("listPage"));
			}
			if (request.getParameter("recordsPerPage") != null) {
				recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
			}CarDAO carDao = new CarDaoImpl();
			List<Car> listOfCars = carDao.findAllActive(recordsPerPage, listPage);
			request.setAttribute("listOfCars", listOfCars);
			int rows = carDao.countAllActive();
			int numberOfPages = (int)Math.ceil((double)rows/recordsPerPage);
			
			request.setAttribute("numberOfPages", numberOfPages);
	        request.setAttribute("listPage", listPage);
	        request.setAttribute("recordsPerPage", recordsPerPage);
		} catch (DaoException e) {
			request.setAttribute("dao_error_message",
					"Some problems with data base. List of our cars must be there. Please refresh the page!");
		}
		return new ForwardPage(PagePath.MAIN);
	}

}
