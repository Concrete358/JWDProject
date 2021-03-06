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
import utils.InvoiceGenerator;

public class CloseOrderCommand implements ICommand {
	private final static Logger logger = Logger.getLogger(CloseOrderCommand.class);
	@Override
	public AbstractPage execute(HttpServletRequest request) {
			int orderId = Integer.parseInt(request.getParameter("order_id"));
			OrderDAO orderDao = new OrderDaoImpl();
			try {
				Optional<Order> orderOptional = orderDao.findById(orderId);
				if(!orderOptional.get().getOrderStatus().name().equalsIgnoreCase("ACTIVE")) {
					request.getSession().setAttribute("close_order_message", "close_message_1.order_info");
					return new RedirectPage(request.getParameter("current_page"));
				} else {
					orderDao.closeOrder(orderId);
					InvoiceGenerator.writeInvoice(orderOptional.get()); 
					request.getSession().setAttribute("close_order_message", "close_message_2.order_info");
					return new RedirectPage(request.getParameter("current_page"));
				}				
			} catch (DaoException e) {
				logger.warn("Error while close order" + orderId, e);
				request.setAttribute("error_message", "close_order.error");
				return new ForwardPage(PagePath.ERROR_500);
			}
	}

}
