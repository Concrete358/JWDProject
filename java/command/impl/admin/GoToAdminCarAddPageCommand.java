package command.impl.admin;

import javax.servlet.http.HttpServletRequest;

import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import utils.CurrentPageExtractor;

public class GoToAdminCarAddPageCommand implements ICommand {

	@Override
	public AbstractPage execute(HttpServletRequest request) {
		request.setAttribute("current_page", CurrentPageExtractor.extract(request));
		return new ForwardPage(PagePath.ADMIN_CAR_ADD);
	}

}
