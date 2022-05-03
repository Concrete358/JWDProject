package command.impl.admin;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.OrderDAO;
import DAO.impl.OrderDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import command.page.RedirectPage;
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
			request.getSession().setAttribute("order_active_message", "activate_message_1.order_info");
			return new RedirectPage(request.getParameter("current_page"));
		} else {		
			orderDao.setOrderStatusActive(orderId);
			request.getSession().setAttribute("order_active_message", "activate_message_2.order_info");
			OrderGenerator.writeOrder(orderOptional.get());
			return new RedirectPage(request.getParameter("current_page"));	
		}
		} catch (DaoException e) {
			logger.error("Something wrong with DB while set order status active", e);
			request.setAttribute("error_message", "set_order_active.error");
			return new ForwardPage(PagePath.ERROR_500);
		}		
	
}
}
