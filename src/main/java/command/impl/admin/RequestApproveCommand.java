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
import entity.OrderFactory;
import exception.DaoException;


public class RequestApproveCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(RequestApproveCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		if (request.getParameter("review_status").equals("true")) {
			request.getSession().setAttribute("approve_message", "approve_message_1.admin_page");
		} else {
			Order order = OrderFactory.getOrderFromRequest(request);
			OrderDAO orderDao = new OrderDaoImpl();
			try {Optional<Order> orderOptional = orderDao.add(order);
				if(orderOptional.isPresent()) {				
				request.getSession().setAttribute("approve_message", "approve_message_2.admin_page");
				} else {
					request.getSession().setAttribute("approve_message", "approve_message_3.admin_page");	
				}
			} catch (DaoException e) {
				logger.warn("Something wrong with DB while approve request and create order", e);
				request.setAttribute("error_message", "admin_approve_request.error");
				return new ForwardPage(PagePath.ERROR_500);
			}
		}
		return new RedirectPage(request.getParameter("current_page"));
	}
}
