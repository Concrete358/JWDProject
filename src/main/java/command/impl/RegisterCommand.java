package command.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import DAO.UserDAO;
import DAO.impl.UserDaoImpl;
import command.ICommand;
import command.page.AbstractPage;
import command.page.ForwardPage;
import constants.PagePath;
import entity.User;
import exception.DaoException;

public class RegisterCommand implements ICommand {
	private static final Logger logger = Logger.getLogger(RegisterCommand.class);
	@Override
	public AbstractPage execute(HttpServletRequest request) {
			User user = new User();
			UserDAO userDao = new UserDaoImpl();
			
			user.setEmail(request.getParameter("email"));
			String password = request.getParameter("password");
			user.setName(request.getParameter("name"));
			user.setLastname(request.getParameter("lastname"));
				String strBirthdate = request.getParameter("birthdate");
				LocalDate ldBirthdate = Date.valueOf(strBirthdate).toLocalDate();	
			user.setBirthdate(ldBirthdate);
			user.setPassportNumber(request.getParameter("passport_number"));
				String strPassportTerm = request.getParameter("passport_term");
				LocalDate ldPassportTerm = Date.valueOf(strPassportTerm).toLocalDate();
			user.setPassportTerm(ldPassportTerm);
			user.setLicenceNumber(request.getParameter("d_licence_number"));
				String strLicenceTerm = request.getParameter("d_licence_term");
				LocalDate ldLicenceTerm = Date.valueOf(strLicenceTerm).toLocalDate();
			user.setLicenceTerm(ldLicenceTerm);
			user.setPhoneNumber(request.getParameter("phone_number"));
			user.setRole("USER");
			try {
				Optional<User> userOptional = userDao.add(user, password);
				if(userOptional.isPresent()) {
					user = userOptional.get();
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					session.setAttribute("role", user.getRole().name());
					session.setAttribute("user_id", user.getId());
					session.setAttribute("user_name", user.getName());
					session.setAttribute("email", user.getEmail());
					
					return new ForwardPage(PagePath.INDEX);
				} else {
					request.setAttribute("RegisterErrorText", "Email is already taken");
					return new ForwardPage(PagePath.REGISTER);
				}
			} catch (DaoException e) {
				logger.warn("Something wrong with DB while register new user", e);
				request.setAttribute("error_message", "Error while register new user");
				return new ForwardPage(PagePath.ERROR_500);
			}
		}
}
