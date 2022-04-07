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

public class RepairOrderCreateCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(RepairOrderCreateCommand.class);
	
	@Override
	public AbstractPage execute(HttpServletRequest request) {
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		int repairCost = (int)Math.round(Double.parseDouble(request.getParameter("repair_cost"))*100);
		String damageDescription = request.getParameter("damage_description");
		RepairOrderDAO repairDao = new RepairOrderDaoImpl();
		OrderDAO orderDao = new OrderDaoImpl();
		try {
			Optional<Order> orderOptional = orderDao.findById(orderId);
			if(!orderOptional.get().getOrderStatus().name().equalsIgnoreCase("ACTIVE")) {
				request.getSession().setAttribute("repair_order_create_result", "claim_message_1.repair_info");
				return new RedirectPage(request.getParameter("current_page"));
			} else {
				if(repairDao.add(orderId, repairCost, damageDescription) == 1) {
					request.getSession().setAttribute("repair_order_create_result", "claim_message_2.repair_info");
				} else {
					request.getSession().setAttribute("repair_order_create_result", "claim_message_3.repair_info");
				}
			}
			return new RedirectPage(request.getParameter("current_page"));	
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while creating repair bill", e);
			request.setAttribute("error_message", "");
			return new ForwardPage(PagePath.ERROR_500);
		}		
	}
}
