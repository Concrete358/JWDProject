package DAO;

import java.util.List;
import java.util.Optional;

import entity.Order;
import exception.DaoException;

public interface OrderDAO extends IDAO<Order> {

	List<Order> findActiveByCarId(int carId) throws DaoException;

	List<Order> findAll(int recordsPerPage, int listPage) throws DaoException;

	int countAll() throws DaoException;

	Optional<Order> findById(int orderId) throws DaoException;

	Optional<Order> add(Order order) throws DaoException;

	void setOrderStatusActive(int orderId) throws DaoException;

	int countAllByUserId(int userId) throws DaoException;

	List<Order> findAllByUserId(int userId, int recordsPerPage, int listPage) throws DaoException;
	
	void closeOrder(int orderId) throws DaoException;
}
