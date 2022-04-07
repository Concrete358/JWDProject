package command.impl.admin;

import javax.servlet.http.HttpServletRequest;

import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import utils.CurrentPageExtractor;

public class GoToRepairOrderCreatePageCommand implements ICommand {

	@Override
	public AbstractPage execute(HttpServletRequest request) {
			int orderId = Integer.parseInt(request.getParameter("order_id"));
			request.setAttribute("order_id", orderId);
			request.setAttribute("current_page", CurrentPageExtractor.extract(request));
			return new ForwardPage(PagePath.ADMIN_REPAIR_ORDER_CREATE);
	}

}
