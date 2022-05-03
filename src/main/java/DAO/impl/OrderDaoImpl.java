package DAO.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import DAO.OrderDAO;
import connectionPool.ConnectionManager;
import entity.Order;
import entity.OrderFactory;
import exception.DaoException;

public class OrderDaoImpl implements OrderDAO {
	Logger logger = Logger.getLogger(OrderDaoImpl.class);

	private static final String SQL_ORDER_CREATE = 
			"INSERT INTO orders (user_id, car_id, request_id) VALUES (?, ?, ?);";
	private static final String SQL_ORDER_CLOSE = "UPDATE orders SET order_status=3 WHERE order_id=?";
	private static final String SQL_ORDER_RENT_CHECK = 
			"SELECT COUNT(a.order_id) FROM (SELECT orders.order_id, rent_start_date, rent_finish_date FROM orders " 
					+ "JOIN requests ON  request_id=req_id WHERE orders.car_id = ? AND (orders.order_status = 1 OR orders.order_status = 2)) a "
					+ "WHERE rent_start_date BETWEEN ? AND ? OR rent_finish_date BETWEEN ? AND ?";
	private static final String SQL_ORDER_FIND_ALL_BY_USER_ID = 
			"SELECT orders.order_id, orders.user_id, concat(users.name, ' ' ,users.lastname) AS 'user_name', orders.car_id, "
			+ "concat(cars.car_maker, ' ',cars.car_model, ' ', cars.number_plate) AS 'car_name', cars.price, requests.rent_start_date, "
			+ "requests.rent_finish_date, orders.request_id, orders.damage_status, orders.repair_order_id, repair_orders.repair_cost, "
			+ "orders.order_status FROM orders JOIN users ON orders.user_id = users.user_id JOIN cars ON orders.car_id = cars.car_id "
			+ "JOIN requests ON orders.request_id = requests.req_id left JOIN repair_orders ON orders.repair_order_id = repair_orders.rep_ord_id "
			+ "WHERE orders.user_id=? ORDER BY orders.order_id DESC LIMIT ? OFFSET ?;";
	private static final String SQL_ORDER_COUNT_ALL = "select count(order_id) from orders;";
	private static final String SQL_ORDER_COUNT_ALL_BY_USER_ID = "select count(order_id) from orders WHERE user_id=?;";
	private static final String SQL_ORDER_FIND_ACTIVE_BY_CAR_ID = "SELECT orders.order_id, orders.user_id, concat(users.name, ' ' ,users.lastname) AS 'user_name', orders.car_id, "
			+ "concat(cars.car_maker, ' ',cars.car_model, ' ', cars.number_plate) AS 'car_name', cars.price, requests.rent_start_date, "
			+ "requests.rent_finish_date, orders.request_id, orders.damage_status, orders.repair_order_id, repair_orders.repair_cost, "
			+ "orders.order_status FROM orders JOIN users ON orders.user_id = users.user_id JOIN cars ON orders.car_id = cars.car_id "
			+ "JOIN requests ON orders.request_id = requests.req_id left JOIN repair_orders ON orders.repair_order_id = repair_orders.rep_ord_id "
			+ "WHERE orders.car_id=? ORDER BY orders.order_id DESC;";
	
	private static final String SQL_ORDER_LIST_SELECT = 
			"SELECT orders.order_id, orders.user_id, concat(users.name, ' ' ,users.lastname) AS 'user_name', orders.car_id, "
			+ "concat(cars.car_maker, ' ',cars.car_model, ' ', cars.number_plate) AS 'car_name', cars.price, requests.rent_start_date, "
			+ "requests.rent_finish_date, orders.request_id, orders.damage_status, orders.repair_order_id, repair_orders.repair_cost, " 
			+ "orders.order_status FROM orders JOIN users ON orders.user_id = users.user_id JOIN cars ON orders.car_id = cars.car_id " 
			+ "JOIN requests ON orders.request_id = requests.req_id left JOIN repair_orders ON orders.repair_order_id = repair_orders.rep_ord_id " 
			+ "ORDER BY orders.order_id DESC LIMIT ? OFFSET ?;";
	private static final String SQL_ORDER_FIND_BY_ID = 
			"SELECT orders.order_id, orders.user_id, concat(users.name, ' ' ,users.lastname) AS 'user_name', orders.car_id, "
			+ "concat(cars.car_maker, ' ',cars.car_model, ' ', cars.number_plate) AS 'car_name', cars.price, requests.rent_start_date, "
			+ "requests.rent_finish_date, orders.request_id, orders.damage_status, orders.repair_order_id, repair_orders.repair_cost, "
			+ "orders.order_status FROM orders JOIN users ON orders.user_id = users.user_id JOIN cars ON orders.car_id = cars.car_id "
			+ "JOIN requests ON orders.request_id = requests.req_id left JOIN repair_orders ON orders.repair_order_id = repair_orders.rep_ord_id "
			+ "WHERE orders.order_id=?;";
	
	private static final String SQL_ORDER_SET_ORDER_ACTIVE = "UPDATE orders SET order_status=2 WHERE order_id=?";
	
	public Optional<Order> add(Order order) throws DaoException {
		try (ConnectionManager cm = new ConnectionManager();
			 PreparedStatement psCheck = cm.getConnection().prepareStatement(SQL_ORDER_RENT_CHECK);
			 PreparedStatement psAdd = cm.getConnection().prepareStatement(SQL_ORDER_CREATE)) {
			psCheck.setInt(1, order.getCarId());
			psCheck.setDate(2, Date.valueOf(order.getRentStartDate()));
			psCheck.setDate(3, Date.valueOf(order.getRentFinishDate()));
			psCheck.setDate(4, Date.valueOf(order.getRentStartDate()));
			psCheck.setDate(5, Date.valueOf(order.getRentFinishDate()));			
			psAdd.setInt(1, order.getUserId());
			psAdd.setInt(2, order.getCarId());
			psAdd.setInt(3, order.getRequestId());
			synchronized (OrderDaoImpl.class) {
					ResultSet rs = psCheck.executeQuery();
                    rs.next();
                	if ( rs.getInt("count(a.order_id)") == 0) {
                        psAdd.executeUpdate();
                        return Optional.of(order);
                    } else {
                    	return Optional.empty();
                    }    
            }
		} catch (SQLException e) {
			logger.error("SQL exception while add new order", e);
			throw new DaoException(e);
		}
	}
	
	public void closeOrder(int orderId) throws DaoException {
		try(ConnectionManager cm = new ConnectionManager();
			PreparedStatement ps = cm.getConnection().prepareStatement(SQL_ORDER_CLOSE)) {
			ps.setInt(1, orderId);
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.warn("SQLException while OrderDaoImpl.closeOrder()", e);
			throw new DaoException("SQLException while OrderDaoImpl.closeOrder()", e);			
		}
	}


	@Override
	public Order read(Order t) {
		logger.error("Unavailable operation to entity <Order>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Order>");
	}

	@Override
	public void update(Order t) {
		logger.error("Unavailable operation to entity <Order>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Order>");
	}

	@Override
	public void delete(Order t) {
		logger.error("Unavailable operation to entity <Order>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Order>");
	}

	public List<Order> findAll(int limit, int pageNumber) throws DaoException {
		try(ConnectionManager cm = new ConnectionManager();
			PreparedStatement ps = cm.getConnection().prepareStatement(SQL_ORDER_LIST_SELECT)) {
			ps.setInt(1, limit);
			int offset = limit * pageNumber - limit;
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();
			List<Order> listOfOrders = new ArrayList<Order>();
			while (rs.next()) {
				Order order = OrderFactory.getOrderFromResultSet(rs);
				listOfOrders.add(order);
			}
			return listOfOrders;
		} catch (SQLException e) {
			logger.warn("SQLException while OrderDaoImpl.findAll()", e);
			throw new DaoException("SQLException while OrderDaoImpl.findAll()", e);
		}
	}
	
	public List<Order> findAllByUserId(int userId, int limit, int pageNumber) throws DaoException {
		try(ConnectionManager cm = new ConnectionManager();
			PreparedStatement ps = cm.getConnection().prepareStatement(SQL_ORDER_FIND_ALL_BY_USER_ID))
		{
			ps.setInt(1, userId);			
			ps.setInt(2, limit);
			int offset = limit * pageNumber - limit;
			ps.setInt(3, offset);
			ResultSet rs = ps.executeQuery();
			List<Order> listOfOrders = new ArrayList<Order>();
			while (rs.next()) {
				Order order = OrderFactory.getOrderFromResultSet(rs);
				listOfOrders.add(order);
			}
			return listOfOrders;
		} catch (SQLException e) {
			logger.warn("SQLException while OrderDaoImpl.findAllByUserId()", e);
			throw new DaoException("SQLException while OrderDaoImpl.findAllByUserId()", e);
		}
	}
	public List<Order> findActiveByCarId(int carId) throws DaoException {
		try(ConnectionManager cm = new ConnectionManager();
			PreparedStatement ps = cm.getConnection().prepareStatement(SQL_ORDER_FIND_ACTIVE_BY_CAR_ID)) 
		{	ps.setInt(1, carId);			
			ResultSet rs = ps.executeQuery();
			List<Order> listOfOrders = new ArrayList<Order>();
			while (rs.next()) {
				Order order = OrderFactory.getOrderFromResultSet(rs);
				listOfOrders.add(order);
			}
			return listOfOrders;
		} catch (SQLException e) {
			logger.warn("SQLException while OrderDaoImpl.findActiveByCarId()", e);
			throw new DaoException("SQLException while OrderDaoImpl.findActiveByCarId()", e);
		} 
	}
	
	public Optional<Order> findById(int orderId) throws DaoException {
		try(ConnectionManager cm = new ConnectionManager();
			PreparedStatement ps = cm.getConnection().prepareStatement(SQL_ORDER_FIND_BY_ID)) {
			ps.setInt(1, orderId);			
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				return Optional.empty();
			} else {
				Order order = OrderFactory.getOrderFromResultSet(rs);
				return Optional.of(order);
				}
		} catch (SQLException e) {
			logger.warn("SQLException while OrderDaoImpl.findById()", e);
			throw new DaoException("SQLException while OrderDaoImpl.findById()", e);
		}
	}
	
	public void setOrderStatusActive(int orderId) throws DaoException {
		try(ConnectionManager cm = new ConnectionManager();
				PreparedStatement ps = cm.getConnection().prepareStatement(SQL_ORDER_SET_ORDER_ACTIVE)) {
			ps.setInt(1, orderId);
			ps.executeUpdate();
		} catch(SQLException e) {
			logger.warn("SQLException while OrderDaoImpl.setOrderStatusActive()", e);
			throw new DaoException("SQLException while OrderDaoImpl.setOrderStatusActive()", e);			
		}
	}

	@Override
	public void create(Order t) {
		logger.error("Unavailable operation to entity <Order>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Order>");
	}

	@Override
	public List<Order> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	public int countAll() throws DaoException {
		try(ConnectionManager cm = new ConnectionManager();
				PreparedStatement ps = cm.getConnection().prepareStatement(SQL_ORDER_COUNT_ALL)) 
		{	ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("count(order_id)");
		} catch (SQLException e) {
			logger.warn("SQLException while OrderDaoImpl.countAll()", e);
			throw new DaoException("SQLException while OrderDaoImpl.countAll()", e);
		}
	}
	
	public int countAllByUserId(int userId) throws DaoException {
		try (ConnectionManager cm = new ConnectionManager();
		PreparedStatement ps = cm.getConnection().prepareStatement(SQL_ORDER_COUNT_ALL_BY_USER_ID))
		{
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("count(order_id)");
		} catch (SQLException e) {
			logger.warn("SQLException while OrderDaoImpl.findAll()", e);
			throw new DaoException("SQLException while OrderDaoImpl.findAll()", e);
		} 
	}

}
