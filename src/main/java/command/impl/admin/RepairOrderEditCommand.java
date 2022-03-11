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
			request.setAttribute("repair_order_edit_message", "INCORRECT OPERATION! Repair order can't be changed for CLOSED rent order.");
			return new ForwardPage((String)request.getSession().getAttribute("current_page"));
		} else {		
			repairDao.update(repairCost, damageDescription, repairOrderId);
			request.setAttribute("repair_order_edit_message", "DONE!");
			return new ForwardPage((String)request.getSession().getAttribute("current_page"));	
		}
		} catch (DaoException e) {
			logger.error("Something wrong with DB while update repair order", e);
			request.setAttribute("error_message", "Error while update repair order");
			return new ForwardPage(PagePath.ERROR_500);
		}		
	
	}

}
