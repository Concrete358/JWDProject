package DAO;

import java.util.Optional;

import entity.User;
import exception.DaoException;

public interface UserDAO extends IDAO<User> {

	Optional<User> findUserByEmailandPassword(String email, String password) throws DaoException;

	Optional<User> add(User user, String password) throws DaoException;

	Optional<User> findUserById(int userId) throws DaoException;
	
	boolean changePassword (int userId, String oldPassword, String newPassword) throws DaoException;
	boolean changeEmail (int userId, String email) throws DaoException;

}
