package command.impl.admin;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.OrderDAO;
import DAO.RepairOrderDAO;
import DAO.impl.OrderDaoImpl;
import DAO.impl.RepairOrderDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import command.page.RedirectPage;
import constants.PagePath;
import entity.Order;
import exception.DaoException;

public class RepairOrderEditCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(RepairOrderEditCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		int repairOrderId = Integer.parseInt(request.getParameter("repair_order_id"));
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		String damageDescription = request.getParameter("damage_description");
		int repairCost = (int)Math.round(Double.parseDouble(request.getParameter("repair_cost"))*100);
		RepairOrderDAO repairDao = new RepairOrderDaoImpl();
		OrderDAO orderDao = new OrderDaoImpl();		
		try {
			Optional<Order> orderOptional = orderDao.findById(orderId);
		if(!orderOptional.get().getOrderStatus().name().equalsIgnoreCase("ACTIVE")) {
			request.getSession().setAttribute("repair_order_edit_message", "repair_order_edit_message_1.repair_info");
			return new RedirectPage(request.getParameter("current_page"));
		} else {		
			repairDao.update(repairCost, damageDescription, repairOrderId);
			request.getSession().setAttribute("repair_order_edit_message", "repair_order_edit_message_2.repair_info");
			return new RedirectPage(request.getParameter("current_page"));	
		}
		} catch (DaoException e) {
			logger.error("Something wrong with DB while update repair order", e);
			request.setAttribute("error_message", "edit_repair_order.error");
			return new ForwardPage(PagePath.ERROR_500);
		}		
	
	}

}
