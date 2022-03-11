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
import utils.CurrentPageExtractor;

public class GoToAdminOrderPageCommand implements ICommand {
		private static final Logger logger = Logger.getLogger(GoToAdminOrderPageCommand.class);

		@Override
		public AbstractPage execute(HttpServletRequest request) {
			request.getSession().setAttribute("current_page", CurrentPageExtractor.extract(request));	
			int orderId = Integer.parseInt(request.getParameter("order_id"));
			OrderDAO orderDao = new OrderDaoImpl();
			try {
				Optional<Order> orderOptional = orderDao.findById(orderId);
				if (orderOptional.isPresent()) {
					Order order = orderOptional.get();
					request.setAttribute("order", order);
					return new ForwardPage(PagePath.ADMIN_ORDER_PAGE);
				} else {
					request.setAttribute("error_message", "ORDER HAVE NOT BEEN FOUNDED!");
					return new ForwardPage(PagePath.ERROR_404);
				}
			} catch (DaoException e) {
				logger.warn("Something wrong with DB while searcing order", e);
				request.setAttribute("error_message", "Error while searcing order");
				return new ForwardPage(PagePath.ERROR_500);
			}
		}
	}
