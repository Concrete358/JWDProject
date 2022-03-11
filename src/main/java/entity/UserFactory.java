package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
