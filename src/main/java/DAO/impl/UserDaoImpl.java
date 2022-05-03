package DAO.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import DAO.UserDAO;
import connectionPool.ConnectionManager;
import entity.User;
import entity.UserFactory;
import exception.DaoException;

public class UserDaoImpl implements UserDAO{
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	private static final String SQL_USER_FIND_BY_EMAIL_AND_PASSWORD = "select * from users where email=? and password=?";
	private static final String SQL_USER_FIND_BY_EMAIL = "select * from users where email=?";
	private static final String SQL_USER_COUNT_BY_ID_AND_PASSWORD = "SELECT COUNT(user_id) FROM users WHERE user_id=? AND password=?";
	private static final String SQL_USER_FIND_BY_ID = "SELECT * FROM users WHERE user_id=?";
	private static final String SQL_USER_UPDATE_PASSWORD = "UPDATE users SET password=? WHERE user_id=?;";
	private static final String SQL_USER_UPDATE_EMAIL = "UPDATE users SET email=? WHERE user_id=?";
	private static final String SQL_USER_ADD = 
			"INSERT INTO users (email, password, name, lastname, birth_date, passport_number, passport_term, d_licence_number, d_licence_term, " 
					+ "phone_number, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public Optional<User> findUserByEmailandPassword(String email, String password) throws DaoException {
		User user = null;
		try(ConnectionManager cm = new ConnectionManager();
			PreparedStatement ps = cm.getConnection().prepareStatement(SQL_USER_FIND_BY_EMAIL_AND_PASSWORD)) {
		String encodedPassword = DigestUtils.sha256Hex(password);
		ps.setString(1, email);
		ps.setString(2, encodedPassword);
		ResultSet rs = ps.executeQuery();
        if(rs.next()) {
			user = UserFactory.getUserFromResultSet(rs);
        }
			return Optional.ofNullable(user);		
		} catch(SQLException e) {
			logger.error("SQL exception while login", e);
			throw new DaoException(e);
		} 
	}
	
	public Optional<User> findUserById(int user_id) throws DaoException {
		User user = null;
		try(ConnectionManager cm = new ConnectionManager();
			PreparedStatement ps = cm.getConnection().prepareStatement(SQL_USER_FIND_BY_ID))
		{
		ps.setInt(1, user_id);
		ResultSet rs = ps.executeQuery();
        if(rs.next()) {
			user = UserFactory.getUserFromResultSet(rs);
        }
			return Optional.ofNullable(user);		
		} catch(SQLException e) {
			logger.error("SQL exception while search user by id", e);
			throw new DaoException(e);
		}
	}
	
	public Optional<User> add(User user, String password) throws DaoException {
		Optional<User> userOptional = Optional.empty();
		try(ConnectionManager cm = new ConnectionManager();
			PreparedStatement psGet = cm.getConnection().prepareStatement(SQL_USER_FIND_BY_EMAIL);
			PreparedStatement psAdd = cm.getConnection().prepareStatement(SQL_USER_ADD, Statement.RETURN_GENERATED_KEYS)) 
		{	
			psGet.setString(1, user.getEmail());
			String encodedPassword = DigestUtils.sha256Hex(password);
			psAdd.setString(1, user.getEmail().toLowerCase());
			psAdd.setString(2, encodedPassword);
			psAdd.setString(3, user.getName());
			psAdd.setString(4, user.getLastname());
				Date birthdate = Date.valueOf(user.getBirthdate());
			psAdd.setDate(5, birthdate);
			psAdd.setString(6, user.getPassportNumber());
				Date passportTerm = Date.valueOf(user.getPassportTerm());
			psAdd.setDate(7, passportTerm);
			psAdd.setString(8,user.getLicenceNumber());
				Date licenceTerm = Date.valueOf(user.getLicenceTerm());
			psAdd.setDate(9, licenceTerm);
			psAdd.setString(10, user.getPhoneNumber());
			psAdd.setString(11, user.getRole().name());		
			synchronized (UserDaoImpl.class) {
	                try (ResultSet rs = psGet.executeQuery()) {
	                    if (!rs.next()) {
	                        psAdd.executeUpdate();
	                    } else {
						return userOptional;
	                    }
	                }
	            }
	            try (ResultSet rsAdded = psAdd.getGeneratedKeys()) {
	                if (rsAdded.next()) {
	                	int userId = rsAdded.getInt(1);
	                    user.setId(userId);
	                    userOptional = Optional.of(user);
	                }
	                return userOptional;
	            }
	        } catch (SQLException e) {
	        	logger.error("SQL exception while add new user", e);
	            throw new DaoException(e);
	        } 
	    }
	
	public boolean changePassword (int userId, String oldPassword, String newPassword) throws DaoException {
		try (ConnectionManager cm = new ConnectionManager();
			PreparedStatement psUpdate = cm.getConnection().prepareStatement(SQL_USER_UPDATE_PASSWORD);
			PreparedStatement psCheck = cm.getConnection().prepareStatement(SQL_USER_COUNT_BY_ID_AND_PASSWORD)) 
		{
			psCheck.setInt(1, userId);
			psCheck.setString(2, DigestUtils.sha256Hex(oldPassword));
			psUpdate.setString(1, DigestUtils.sha256Hex(newPassword));
			psUpdate.setInt(2, userId);
			synchronized (UserDaoImpl.class) {
                try (ResultSet rs = psCheck.executeQuery()) {
                	rs.next();
                    if (rs.getInt("count(user_id)") == 1) {
                        psUpdate.executeUpdate();
                        return true;
                    } else {
                    	return false;
                    }
                }
			}
			} catch (SQLException e) {
        	logger.error("SQL exception while user " + userId + "change password", e);
            throw new DaoException(e);
        	} 
		}
	
	public boolean changeEmail (int userId, String email) throws DaoException {
		try (ConnectionManager cm = new ConnectionManager();
			PreparedStatement psUpdate = cm.getConnection().prepareStatement(SQL_USER_UPDATE_EMAIL);
			PreparedStatement psCheck = cm.getConnection().prepareStatement(SQL_USER_FIND_BY_EMAIL))
		{
			psCheck.setString(1, email);
			psUpdate.setString(1, email);
			psUpdate.setInt(2, userId);
			synchronized (UserDaoImpl.class) {
                try (ResultSet rs = psCheck.executeQuery()) {
                    if (!rs.next()) {
                        psUpdate.executeUpdate();
                        return true;
                    } else {
                    	return false;
                    }
                }
			}
        } catch (SQLException e) {
        	logger.error("SQL exception while user " + userId + "change email", e);
            throw new DaoException(e);
        }
	}
                
	

	@Override
	public List<User> findAll() throws DaoException {
		logger.error("Unavailable operation to entity <User>");
        throw new UnsupportedOperationException("Unavailable operation to entity <User>");
	}

	@Override
	public void create(User t) {
		logger.error("Unavailable operation to entity <User>");
        throw new UnsupportedOperationException("Unavailable operation to entity <User>");
	}

	@Override
	public User read(User t) {
		logger.error("Unavailable operation to entity <User>");
        throw new UnsupportedOperationException("Unavailable operation to entity <User>");
	}

	@Override
	public void update(User t) {
		logger.error("Unavailable operation to entity <User>");
        throw new UnsupportedOperationException("Unavailable operation to entity <User>");
	}

	@Override
	public void delete(User t) {
		logger.error("Unavailable operation to entity <User>");
        throw new UnsupportedOperationException("Unavailable operation to entity <User>");
	}

}
