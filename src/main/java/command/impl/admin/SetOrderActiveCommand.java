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
import exception.DaoException;
import utils.OrderGenerator;

public class SetOrderActiveCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(SetOrderActiveCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		OrderDAO orderDao = new OrderDaoImpl();		
		try {
			Optional<Order> orderOptional = orderDao.findById(orderId);
		if(!orderOptional.get().getOrderStatus().name().equalsIgnoreCase("INACTIVE")) {
			request.setAttribute("order_active_message", "INCORRECT OPERATION! Active or closed order can't be activated.");
			return new ForwardPage((String)request.getSession().getAttribute("current_page"));
		} else {		
			orderDao.setOrderStatusActive(orderId);
			request.setAttribute("order_active_message", "ORDER SET ACTIVE. PDF-order generated!");
			OrderGenerator.writeOrder(orderOptional.get());
			return new ForwardPage((String)request.getSession().getAttribute("current_page"));	
		}
		} catch (DaoException e) {
			logger.error("Something wrong with DB while set order status active", e);
			request.setAttribute("error_message", "Error while set order status active");
			return new ForwardPage(PagePath.ERROR_500);
		}		
	
}
}
