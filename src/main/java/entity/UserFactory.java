package entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

public class UserFactory {

	public static User getUserFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setEmail(rs.getString("email"));
		user.setName(rs.getString("name"));
		user.setLastname(rs.getString("lastname"));
		user.setBirthdate(rs.getDate("birth_date").toLocalDate());
		user.setPassportNumber(rs.getString("passport_number"));
		user.setPassportTerm(rs.getDate("passport_term").toLocalDate());
		user.setLicenceNumber(rs.getString("d_licence_number"));
		user.setLicenceTerm(rs.getDate("d_licence_term").toLocalDate());
		user.setPhoneNumber(rs.getString("phone_number"));
		user.setRole(rs.getString("role"));
		return user;
	}
	
	public static User getUserFromRequest(HttpServletRequest request) {
		User user = new User();			
		user.setEmail(request.getParameter("email").toLowerCase());
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
		return user;
	}
}
