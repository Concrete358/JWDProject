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
				request.setAttribute("repair_order_create_result", "INCORRECT OPERATION! Damage can't be claimed on inactive or closed order.");
				return new ForwardPage((String)request.getSession().getAttribute("current_page"));
			} else {
				if(repairDao.add(orderId, repairCost, damageDescription) == 1) {
					request.setAttribute("repair_order_create_result", "BILL HAVE BEEN CREATED!");
				} else {
					request.setAttribute("repair_order_create_result", "BILL HAVE NOT BEEN CREATED! PROBABLY IT IS ALREADY EXIST. CHECK AT ORDER PAGE.");
				}
			}
			return new ForwardPage((String)request.getSession().getAttribute("current_page"));	
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while creating repair bill", e);
			request.setAttribute("error_message", "Error while creating repair bill.");
			return new ForwardPage(PagePath.ERROR_500);
		}		
	}
}
