package command.impl.admin;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.OrderDAO;
import DAO.impl.OrderDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import entity.Order;
import entity.OrderFactory;
import exception.DaoException;


public class RequestApproveCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(RequestApproveCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		if (request.getParameter("review_status").equals("true")) {
			request.setAttribute("approve_message", "This request is already reviewed!");
		} else {
			Order order = OrderFactory.getOrderFromRequest(request);
			OrderDAO orderDao = new OrderDaoImpl();
			try {Optional<Order> orderOptional = orderDao.add(order);
				if(orderOptional.isPresent()) {				
				request.setAttribute("approve_message", "Request approved! New order created.");
				} else {
					request.setAttribute("approve_message", "Request can't be approved! The car is booked for this period.");	
				}
			} catch (DaoException e) {
				logger.warn("Something wrong with DB while approve request and create order", e);
				request.setAttribute("error_message", "Error while approving request and creating order");
				return new ForwardPage(PagePath.ERROR_500);
			}
		}
		return new ForwardPage((String) request.getSession().getAttribute("current_page"));
	}
}
