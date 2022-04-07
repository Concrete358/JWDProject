package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import DAO.CarDAO;
import connectionPool.ConnectionPool;
import entity.car.Car;
import entity.car.CarFactory;
import exception.DaoException;

import org.apache.log4j.Logger;

public class CarDaoImpl implements CarDAO {
	private static final Logger logger = Logger.getLogger(CarDaoImpl.class);

	private static final String SQL_CAR_FIND_ALL = "SELECT * FROM cars LIMIT ? OFFSET ?";
	private static final String SQL_CAR_FIND_ALL_ACTIVE = "SELECT * FROM cars WHERE block=0 LIMIT ? OFFSET ?";
	private static final String SQL_CAR_FIND_BY_ID = "SELECT * FROM cars WHERE car_id=?";
	private static final String SQL_CAR_COUNT_ALL = "select count(car_id) from cars;";
	private static final String SQL_CAR_COUNT_ALL_ACTIVE = "select count(car_id) from cars WHERE block=0;";
	
	private static final String SQL_CAR_ADD = "INSERT INTO cars (vin, number_plate, car_maker, car_model, engine_type, drive_type, power, price)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_CAR_FIND_BY_VIN = "SELECT * FROM cars WHERE vin=?";
	private static final String SQL_CAR_FIND_BY_NUMBER = "SELECT * FROM cars WHERE number_plate=?";
	private static final String SQL_CAR_CHANGE_BLOCK = "UPDATE cars SET block=? WHERE car_id=?";
	
	public void changeBlock(int carId, boolean blockStatus) throws DaoException {
		Connection conn = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement ps = conn.prepareStatement(SQL_CAR_CHANGE_BLOCK)) {
			ps.setBoolean(1, blockStatus);
			ps.setInt(2, carId);
			ps.execute();
		} catch (SQLException e) {
			logger.warn("SQLException while CarDaoImpl.changeBlock()", e);
			throw new DaoException("SQLException while CarDaoImpl.changeBlock()", e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}

	public Optional<Car> add(Car car) throws DaoException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement psAdd = connection.prepareStatement(SQL_CAR_ADD, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement psCheckVin = connection.prepareStatement(SQL_CAR_FIND_BY_VIN);
				PreparedStatement psCheckNumber = connection.prepareStatement(SQL_CAR_FIND_BY_NUMBER)) {
			psCheckNumber.setString(1, car.getNumberPlate());
			psCheckVin.setString(1, car.getVinNumber());

			psAdd.setString(1, car.getVinNumber());
			psAdd.setString(2, car.getNumberPlate());
			psAdd.setString(3, car.getCarMaker());
			psAdd.setString(4, car.getCarModel());
			psAdd.setString(5, car.getEngineType().name());
			psAdd.setString(6, car.getDrivenWheels().name());
			psAdd.setInt(7, car.getPower());
			psAdd.setInt(8, car.getPricePerDay().getIntValue());
			ResultSet rs = psCheckVin.executeQuery();
			if (!rs.next()) {
				rs = psCheckNumber.executeQuery();
				if (!rs.next()) {
					psAdd.executeUpdate();
				}
			} else {
				return Optional.empty();
			}
			ResultSet rsAdded = psAdd.getGeneratedKeys();
				if (rsAdded.next()) {
                	int carId = rsAdded.getInt(1);
                    car.setId(carId);
                    return Optional.of(car);
			} else {
				return Optional.empty();
			}
		}catch(SQLException e) {
			logger.error("SQL exception while add new car", e);
			throw new DaoException(e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}

	}

	public Optional<Car> findById(int id) throws DaoException {
		Connection conn = null;
		PreparedStatement ps = null;
		Optional<Car> resultCar = Optional.empty();
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement(SQL_CAR_FIND_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Car car = CarFactory.getCarFromResultSet(rs);
				resultCar = Optional.of(car);
			}
			return resultCar;
		} catch (SQLException e) {
			logger.warn("SQLException while CarDaoImpl.findById()", e);
			throw new DaoException("SQLException while CarDaoImpl.findById()", e);
		} finally {
			closePreparedStatement(ps);
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}

	public int countAll() throws DaoException {
		Connection conn = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement ps = conn.prepareStatement(SQL_CAR_COUNT_ALL)) {
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("count(car_id)");
		} catch (SQLException e) {
			logger.warn("SQLException while CarDaoImpl.countAll()", e);
			throw new DaoException("SQLException while CarDaoImpl.countAll()", e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}
	
	public int countAllActive() throws DaoException {
		Connection conn = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement ps = conn.prepareStatement(SQL_CAR_COUNT_ALL_ACTIVE)) {
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("count(car_id)");
		} catch (SQLException e) {
			logger.warn("SQLException while CarDaoImpl.countAllActive()", e);
			throw new DaoException("SQLException while CarDaoImpl.countAllActive()", e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}

	@Override
	public void create(Car t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Car read(Car t) {
		logger.error("Unavailable operation to entity <Car>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Car>");
	}

	@Override
	public void update(Car t) {
		logger.error("Unavailable operation to entity <Car>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Car>");

	}

	@Override
	public void delete(Car t) {
		logger.error("Unavailable operation to entity <Car>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Car>");

	}

	public List<Car> findAll(int limit, int pageNumber) throws DaoException {
		Connection conn = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement ps = conn.prepareStatement(SQL_CAR_FIND_ALL)) {
			ps.setInt(1, limit);
			int offset = limit * pageNumber - limit;
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();
			List<Car> listOfCars = new ArrayList<Car>();
			while (rs.next()) {
				Car car = CarFactory.getCarFromResultSet(rs);
				listOfCars.add(car);
			}
			return listOfCars;
		} catch (SQLException e) {
			logger.warn("SQLException while CarDaoImpl.findAll()", e);
			throw new DaoException("SQLException while CarDaoImpl.findAll()", e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}
	
	public List<Car> findAllActive(int limit, int pageNumber) throws DaoException {
		Connection conn = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement ps = conn.prepareStatement(SQL_CAR_FIND_ALL_ACTIVE)) {
			ps.setInt(1, limit);
			int offset = limit * pageNumber - limit;
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();
			List<Car> listOfCars = new ArrayList<Car>();
			while (rs.next()) {
				Car car = CarFactory.getCarFromResultSet(rs);
				listOfCars.add(car);
			}
			return listOfCars;
		} catch (SQLException e) {
			logger.warn("SQLException while CarDaoImpl.findAllActive()", e);
			throw new DaoException("SQLException while CarDaoImpl.findAllActive()", e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}

	@Override
	public List<Car> findAll() throws DaoException {
		logger.error("Unavailable operation to entity <Car>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Car>");
	}

	public void changeParameter(int carId, String paramName, String paramValue) throws DaoException {
		Connection conn = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement ps = conn.prepareStatement("UPDATE cars SET " + paramName + "= ? WHERE car_id=?")) {

			if (paramName.equalsIgnoreCase("power")) {
				ps.setInt(1, Integer.parseInt(paramValue));
			} else if (paramName.equalsIgnoreCase("price")){
				int priceValue = (int)Math.round(Double.parseDouble(paramValue)*100);
				ps.setInt(1, priceValue);
			} else {
				ps.setString(1, paramValue);
			}
			ps.setInt(2, carId);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.warn("SQLException while CarDaoImpl.changeParameter()", e);
			throw new DaoException("SQLException while CarDaoImpl.changeParameter()", e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}
}
