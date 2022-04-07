package command.page;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractPage {
	
	private String path;
	
	public AbstractPage(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public abstract void finishRequest(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException;
}

