package command.impl.admin;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DAO.RepairOrderDAO;
import DAO.impl.RepairOrderDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import entity.RepairOrder;
import exception.DaoException;
import utils.CurrentPageExtractor;

public class GoToRepairOrderInfoPageCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(GoToRepairOrderInfoPageCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		int repairOrderId = Integer.parseInt(request.getParameter("repair_order_id"));
		request.setAttribute("current_page", CurrentPageExtractor.extract(request));
		RepairOrderDAO repairDao = new RepairOrderDaoImpl();
		try {
			Optional<RepairOrder> repairOptional = repairDao.findByRepairOrderId(repairOrderId);
			if (repairOptional.isPresent()) {
				request.setAttribute("order", repairOptional.get());
				return new ForwardPage(PagePath.ADMIN_REPAIR_ORDER_INFO);
			} else {
				request.setAttribute("error_message", "REPAIR ORDER PAGE HAVE NOT BEEN FOUNDED!");
				return new ForwardPage(PagePath.ERROR_403);
			}
		} catch (DaoException e) {
			logger.warn("Something wrong with DB while searcing repair order info", e);
			request.setAttribute("error_message", "Error while searcing repair order info");
			return new ForwardPage(PagePath.ERROR_500);
		}
	}
}