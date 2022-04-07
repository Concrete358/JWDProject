package filter;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import constants.PagePath;

/**
 * Servlet Filter implementation class CommandFilter
 */
@WebFilter(urlPatterns = { "/FrontController"})
public class CommandFilter extends HttpFilter implements Filter {
	private static final Logger logger = Logger.getLogger(CommandFilter.class);
	private static final long serialVersionUID = 1L;
	
	private Set<String> adminCommands;
	private Set<String> userCommands;
	private Set<String> guestCommands;

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		String commandName = request.getParameter("command");
		if (commandName == null || commandName.equals("")) {
			response.sendRedirect(request.getContextPath() + PagePath.INDEX);
			return;
		}			
			Optional<Object> roleOptional = Optional.ofNullable(session.getAttribute("role"));
			String role = "";
			if (roleOptional.isPresent()) {
				role = roleOptional.get().toString().toUpperCase();
			}
			boolean isAccept = switch (role) {
			case "USER" -> userCommands.contains(commandName.toUpperCase());
			case "ADMIN" -> adminCommands.contains(commandName.toUpperCase());
			default -> guestCommands.contains(commandName.toUpperCase());
			};
			if (!isAccept) {
				logger.warn("forbiden command - " + commandName + " - from adress: " + request.getRemoteAddr() + role );
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
			}
		chain.doFilter(request,response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {		
		adminCommands = Set.of("GO_TO_MAIN_PAGE", "GO_TO_LOGIN_PAGE", "GO_TO_REGISTER_PAGE", "GO_TO_CAR_PAGE",
				"CREATE_CAR", "CHANGE_CAR_BLOCK", "SET_ORDER_ACTIVE", "GO_TO_ADMIN_ORDER_PAGE",
				"GO_TO_ADMIN_REPAIR_ORDER_LIST_PAGE", "GO_TO_ADMIN_CAR_EDIT_PAGE", "GO_TO_ADMIN_CAR_INFO_PAGE",
				"GO_TO_ADMIN_CAR_ADD_PAGE", "GO_TO_USER_INFO_PAGE", "GO_TO_ADMIN_REQUEST_LIST_PAGE",
				"GO_TO_ADMIN_ORDER_LIST_PAGE", "GO_TO_REPAIR_ORDER_INFO_PAGE", "GO_TO_REPAIR_ORDER_CREATE_PAGE",
				"GO_TO_ADMIN_CAR_LIST_PAGE", "CHANGE_CAR_PARAMETER", "REQUEST_APPROVE", "REQUEST_REJECT",
				"REPAIR_ORDER_EDIT", "REPAIR_ORDER_CREATE", "CLOSE_ORDER", "LOGIN", "REGISTER", "LOGOUT",
				"GO_TO_USER_PAGE", "GO_TO_REQUEST_EDIT_PAGE", "GO_TO_USER_ORDER_LIST_PAGE",
				"GO_TO_USER_REQUEST_LIST_PAGE", "CHANGE_EMAIL", "CHANGE_PASSWORD", "REQUEST_GENERATE", "REQUEST_EDIT",
				"CANCEL_REQUEST","SET_LOCALE");

		userCommands = Set.of("GO_TO_MAIN_PAGE", "GO_TO_LOGIN_PAGE", "GO_TO_REGISTER_PAGE", "GO_TO_CAR_PAGE", "LOGIN",
				"REGISTER", "LOGOUT", "GO_TO_USER_PAGE", "GO_TO_REQUEST_EDIT_PAGE", "GO_TO_USER_ORDER_LIST_PAGE",
				"GO_TO_USER_REQUEST_LIST_PAGE", "CHANGE_EMAIL", "CHANGE_PASSWORD", "REQUEST_GENERATE", "REQUEST_EDIT",
				"CANCEL_REQUEST", "SET_LOCALE");

		guestCommands = Set.of("LOGIN", "REGISTER", "LOGOUT", "GO_TO_MAIN_PAGE", "GO_TO_LOGIN_PAGE",
				"GO_TO_REGISTER_PAGE", "GO_TO_CAR_PAGE", "SET_LOCALE");	
	}

}
