package command.impl.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.CarDAO;
import DAO.impl.CarDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import exception.DaoException;

public class ChangeCarBlockCommand implements ICommand {
	private static Logger logger = Logger.getLogger(ChangeCarBlockCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		int carId = Integer.parseInt(request.getParameter("car_id"));
		boolean block = Boolean.parseBoolean(request.getParameter("block"));
		CarDAO carDao = new CarDaoImpl();
		try {
			carDao.changeBlock(carId, block);
			return new ForwardPage((String) request.getSession().getAttribute("current_page"));
		} catch (DaoException e) {
			logger.error("Error while block/unblock car", e);
			request.setAttribute("error_message", "Error while block/unblock car.");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}

}
