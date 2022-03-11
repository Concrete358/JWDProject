package frontcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import command.CommandFactory;
import command.ICommand;
import command.page.AbstractPage;
import constants.PagePath;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FrontController.class);
	
	static {
		new DOMConfigurator().doConfigure("E:\\Java Web Development (Study)\\JWDproject\\src\\main\\resources/log4j.xml", LogManager.getLoggerRepository());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandFactory factory = new CommandFactory();
		ICommand command = factory.defineCommand(request);
		if(command != null) {
			AbstractPage page = command.execute(request);
			page.finishRequest(request, response);
		} else {
			logger.warn("unsupported command: " + request.getParameter("command") + " from " + request.getRequestURL());
			request.setAttribute("error_message", "UNSUPPORTED COMMAND => " + request.getAttribute("incorrect_command_name"));
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_500).forward(request, response);
		}
	}
	

}

