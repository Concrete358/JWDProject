package DAO.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import DAO.RequestDAO;
import connectionPool.ConnectionPool;
import entity.Request;
import entity.RequestFactory;
import exception.DaoException;

public class RequestDaoImpl implements RequestDAO {
	private static final Logger logger = Logger.getLogger(RequestDaoImpl.class);


	private static final String SQL_REQUEST_FIND_ALL_BY_USER_ID = 
			"SELECT requests.req_id, requests.user_id, concat(users.name, ' ', users.lastname) as 'user_name', requests.car_id, "
			+ "concat(cars.car_maker, ' ', cars.car_model, ' ', cars.number_plate) as 'car_name', cars.price, "
			+ "requests.order_id, requests.rent_start_date, requests.rent_finish_date, requests.review_status, req_approve_status, reject_reason "
			+ "FROM requests JOIN cars ON requests.car_id = cars.car_id JOIN users ON requests.user_id = users.user_id WHERE requests.user_id=? " 
			+ " ORDER BY requests.req_id DESC LIMIT ? OFFSET ?";
	private static final String SQL_REQUEST_FIND_BY_USER_CAR_PERIOD = 
			"SELECT * FROM requests WHERE user_id=? and car_id=? and rent_start_date=? and rent_finish_date=? and review_status='0';";
	private static final String SQL_REQUEST_ADD = "INSERT INTO requests (user_id , car_id, rent_start_date, rent_finish_date) VALUES (?, ?, ? ,?);";
	private static final String SQL_REQUEST_UPDATE_REJECT = "UPDATE requests SET review_status=1, req_approve_status=0, reject_reason=? WHERE req_id=?;";
	private static final String SQL_REQUEST_COUNT_ALL = "select count(req_id) from requests;";
	private static final String SQL_REQUEST_COUNT_ALL_BY_USER_ID = "select count(req_id) from requests where user_id=?;";
	private static final String SQL_REQUEST_LIST_SELECT = 
			"SELECT requests.req_id, requests.user_id, concat(users.name, ' ', users.lastname) as 'user_name', requests.car_id, " 
			+ " concat(cars.car_maker, ' ', cars.car_model, ' ', cars.number_plate) as 'car_name', cars.price, "
			+ "requests.order_id, requests.rent_start_date, requests.rent_finish_date, requests.review_status, req_approve_status, reject_reason "
			+ "FROM requests JOIN cars ON requests.car_id = cars.car_id JOIN users ON requests.user_id = users.user_id ORDER BY requests.req_id DESC LIMIT ? OFFSET ?";
	private static final String SQL_REQUEST_SELECT_BY_ID = "SELECT * FROM requests WHERE req_id=?";
	private static final String SQL_REQUEST_FIND_BY_ID = "SELECT requests.req_id, requests.user_id, concat(users.name, ' ', users.lastname) as 'user_name', requests.car_id, "
			+ "concat(cars.car_maker, ' ', cars.car_model, ' ', cars.number_plate) as 'car_name', cars.price, "
			+ "requests.order_id, requests.rent_start_date, requests.rent_finish_date, requests.review_status, req_approve_status, reject_reason "
			+ "FROM requests JOIN cars ON requests.car_id = cars.car_id JOIN users ON requests.user_id = users.user_id WHERE requests.req_id=? ";	
	private static final String SQL_REQUEST_DELETE_BY_ID = "DELETE FROM requests WHERE req_id=?";
	private static final String SQL_REQUEST_CHANGE_DATES = "UPDATE requests SET rent_start_date=?, rent_finish_date=? WHERE req_id=?";
			
	public Optional<Request> add(Request rentRequest) throws DaoException {
		Connection connection = null;
		PreparedStatement psGet = null;
		PreparedStatement psAdd = null;
		Optional<Request> requestOptional = Optional.empty();
		try {
			connection = ConnectionPool.getInstance().getConnection();
			psGet = connection.prepareStatement(SQL_REQUEST_FIND_BY_USER_CAR_PERIOD);
			psGet.setInt(1, rentRequest.getUserId());
			psGet.setInt(2, rentRequest.getCarId());
			Date startDate = Date.valueOf(rentRequest.getRentStartDate());
			psGet.setDate(3, startDate);
			Date finishDate = Date.valueOf(rentRequest.getRentFinishDate());
			psGet.setDate(4, finishDate);
			psAdd = connection.prepareStatement(SQL_REQUEST_ADD, Statement.RETURN_GENERATED_KEYS);
			psAdd.setInt(1, rentRequest.getUserId());
			psAdd.setInt(2, rentRequest.getCarId());
			psAdd.setDate(3, startDate);
			psAdd.setDate(4, finishDate);
			try (ResultSet rs = psGet.executeQuery()) {
				if (!rs.next()) {
					psAdd.executeUpdate();
				} else {
					return requestOptional;
				}
			}
			try (ResultSet rsAdded = psAdd.getGeneratedKeys()) {
				if (rsAdded.next()) {
					int requestId = rsAdded.getInt(1);
					rentRequest.setRequestId(requestId);
					requestOptional = Optional.of(rentRequest);

				}
				return requestOptional;
			}
		} catch (SQLException e) {
			logger.error("SQL exception while add new request", e);
			throw new DaoException(e);
		} finally {
			closePreparedStatement(psAdd);
			closePreparedStatement(psGet);
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}

	@Override
	public Request read(Request t) {
		logger.error("Unavailable operation to entity <Request>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Request>");
	}

	@Override
	public void update(Request t) {
		logger.error("Unavailable operation to entity <Request>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Request>");
	}

	@Override
	public void delete(Request t) {
		logger.error("Unavailable operation to entity <Request>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Request>");
	}
	
	public boolean deleteById(int id) throws DaoException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		try(PreparedStatement psGet = connection.prepareStatement(SQL_REQUEST_SELECT_BY_ID);
			PreparedStatement psDelete = connection.prepareStatement(SQL_REQUEST_DELETE_BY_ID)){
			psGet.setInt(1, id);
			psDelete.setInt(1, id);
			ResultSet rs = psGet.executeQuery();
			rs.next();
			if(rs.getBoolean("review_status") == false) {
				psDelete.executeUpdate();
				return true;
			} else {
				return false;
			}			
		} catch (SQLException e) {
			logger.error("SQLException while delete request", e);
			throw new DaoException(e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}	
	
	public List<Request> findAll(int limit, int pageNumber) throws DaoException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement(SQL_REQUEST_LIST_SELECT);
			ps.setInt(1, limit);
			int offset = limit * pageNumber - limit;
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();
			List<Request> listOfRequests = new ArrayList<Request>();
			while (rs.next()) {
				Request rentRequest = RequestFactory.getRequestFromFactory(rs);
				listOfRequests.add(rentRequest);
			}
			return listOfRequests;
		} catch (SQLException e) {
			logger.warn("SQLException while RequestDaoImpl.findAll()", e);
			throw new DaoException("SQLException while RequestDaoImpl.findAll()", e);
		} finally {
			closePreparedStatement(ps);
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}
	
	public List<Request> findAllByUserId(int userId, int limit, int pageNumber) throws DaoException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement(SQL_REQUEST_FIND_ALL_BY_USER_ID);
			ps.setInt(1, userId);
			ps.setInt(2, limit);
			int offset = limit * pageNumber - limit;
			ps.setInt(3, offset);
			ResultSet rs = ps.executeQuery();
			List<Request> listOfRequests = new ArrayList<Request>();
			while (rs.next()) {
				Request rentRequest = RequestFactory.getRequestFromFactory(rs);
				listOfRequests.add(rentRequest);
			}
			return listOfRequests;
		} catch (SQLException e) {
			logger.warn("SQLException while RequestDaoImpl.findAllByUserId()", e);
			throw new DaoException("SQLException while RequestDaoImpl.findAllByUserId()", e);
		} finally {
			closePreparedStatement(ps);
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}

	@Override
	public void create(Request t) {
		logger.error("Unavailable operation to entity <Request>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Request>");
	}

	public void reject(int request_id, String reason) throws DaoException {
		Connection connection = null;
		PreparedStatement psReject = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			psReject = connection.prepareStatement(SQL_REQUEST_UPDATE_REJECT);
			psReject.setString(1, reason);
			psReject.setInt(2, request_id);
			psReject.execute();
		} catch (SQLException e) {
			logger.error("SQL exception while reject request", e);
			throw new DaoException(e);
		} finally {
			closePreparedStatement(psReject);
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}
	
	public int countAll() throws DaoException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement(SQL_REQUEST_COUNT_ALL);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("count(req_id)");
		} catch (SQLException e) {
			logger.warn("SQLException while OrderDaoImpl.findAll()", e);
			throw new DaoException("SQLException while RequestDaoImpl.countAll()", e);
		} finally {
			closePreparedStatement(ps);
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}
	
	public int countAllByUserId(int userId) throws DaoException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement(SQL_REQUEST_COUNT_ALL_BY_USER_ID);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("count(req_id)");
		} catch (SQLException e) {
			logger.warn("SQLException while OrderDaoImpl.findAll()", e);
			throw new DaoException("SQLException while RequestDaoImpl.countAll()", e);
		} finally {
			closePreparedStatement(ps);
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}

	@Override
	public List<Request> findAll() throws DaoException {
		logger.error("Unavailable operation to entity <Request>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Request>");
	}

	@Override
	public boolean changeDates(int id, Date rentStartDate, Date rentFinishDate) throws DaoException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		try(PreparedStatement psGet = connection.prepareStatement(SQL_REQUEST_SELECT_BY_ID);
			PreparedStatement psChange = connection.prepareStatement(SQL_REQUEST_CHANGE_DATES)){
			psGet.setInt(1, id);
			psChange.setDate(1, rentStartDate);
			psChange.setDate(2, rentFinishDate);
			psChange.setInt(3, id);
			ResultSet rs = psGet.executeQuery();
			rs.next();
			if(rs.getBoolean("review_status") == false) {
				psChange.executeUpdate();
				return true;
			} else {
				return false;
			}			
		} catch (SQLException e) {
			logger.error("SQLException while delete request", e);
			throw new DaoException(e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}

	@Override
	public Optional<Request> findById(int requestId) throws DaoException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		try(PreparedStatement psGet = connection.prepareStatement(SQL_REQUEST_FIND_BY_ID)) {
			psGet.setInt(1, requestId);
			ResultSet rs = psGet.executeQuery();
			if(rs.next()) {
				Request req = RequestFactory.getRequestFromFactory(rs);
				return Optional.of(req);
			} else {
				return Optional.empty();
			}	
		} catch (SQLException e) {
			logger.error("SQLException while find request by ID", e);
			throw new DaoException(e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}	
}	


