package command.impl.admin;

import java.util.List;

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

public class GoToAdminRepairOrderListPageCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(GoToAdminRepairOrderListPageCommand.class);

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		if(!request.getSession().getAttribute("role").toString().toUpperCase().equals("ADMIN")) {
			request.setAttribute("error_message", "You don't have permission to access this page.");
			return new ForwardPage(PagePath.ERROR_403);
		}
		try {
			int listPage = 1;
			int recordsPerPage = 20;
			request.setAttribute("current_page", CurrentPageExtractor.extract(request));
			if(request.getParameter("listPage") != null) {
			listPage = Integer.parseInt(request.getParameter("listPage"));
			}
			if(request.getParameter("recordsPerPage") !=null) {
		    recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
			}
			RepairOrderDAO repairOrderDao = new RepairOrderDaoImpl();
			List<RepairOrder> listOfRepairOrders = repairOrderDao.findAll(recordsPerPage, listPage);
			request.setAttribute("listOfRepairOrders", listOfRepairOrders);

			int rows = repairOrderDao.countAll();
			int numberOfPages = (int)Math.ceil((double)rows/recordsPerPage);
			
			request.setAttribute("numberOfPages", numberOfPages);
	        request.setAttribute("listPage", listPage);
	        request.setAttribute("recordsPerPage", recordsPerPage);
			} catch(DaoException e) {
			logger.warn("Error while generate list of orders", e);
			request.setAttribute("dao_error_message", "Some problems with data base. List of repair orders must be there. Please refresh the page!");
			}
			return new ForwardPage(PagePath.ADMIN_REPAIR_ORDER_LIST);
		}
}
