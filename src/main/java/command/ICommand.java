package command;

import javax.servlet.http.HttpServletRequest;
import command.page.AbstractPage;


public interface ICommand {
	AbstractPage execute(HttpServletRequest request);
}
