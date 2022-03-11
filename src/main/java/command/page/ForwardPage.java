package command.page;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardPage extends AbstractPage {
		
	public ForwardPage(String path) {
		super(path);
	}
	
	@Override
	public void finishRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(getPath());
		dispatcher.forward(request, response);
	}
	
}
