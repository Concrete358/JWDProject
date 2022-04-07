package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;


import DAO.RepairOrderDAO;
import connectionPool.ConnectionPool;
import entity.RepairOrder;
import entity.RepairOrderFactory;
import exception.DaoException;

public class RepairOrderDaoImpl implements RepairOrderDAO {
	
	private static final Logger logger = Logger.getLogger(RepairOrderDaoImpl.class);
	
	private static final String SQL_REPAIR_ORDER_ADD = "INSERT INTO repair_orders (order_id, repair_cost, damage_description) VALUES (?, ?, ?);";
	private static final String SQL_REPAIR_ORDER_FIND_BY_ORDER_ID = "SELECT * FROM repair_orders WHERE order_id=?;";
	private static final String SQL_REPAIR_ORDER_FIND_BY_REPAIR_ORDER_ID = "SELECT * FROM repair_orders WHERE rep_ord_id=?;";
	private static final String SQL_REPAIR_ORDER_LIST_SELECT = "SELECT * FROM repair_orders LIMIT ? OFFSET ?";
	private static final String SQL_REPAIR_ORDER_COUNT_ALL = "SELECT COUNT(rep_ord_id) FROM repair_orders;";
	private static final String SQL_REPAIR_ORDER_UPDATE = "UPDATE repair_orders SET repair_cost=?, damage_description=? WHERE rep_ord_id=?";
	
	
	@Override
	public void create(RepairOrder t) {
		logger.error("Unavailable operation to entity <RepairOrder>");
        throw new UnsupportedOperationException("Unavailable operation to entity <RepairOrder>");
	}

	@Override
	public RepairOrder read(RepairOrder t) {
		logger.error("Unavailable operation to entity <RepairOrder>");
        throw new UnsupportedOperationException("Unavailable operation to entity <RepairOrder>");
	}

	@Override
	public void update(RepairOrder t) {
		logger.error("Unavailable operation to entity <RepairOrder>");
        throw new UnsupportedOperationException("Unavailable operation to entity <RepairOrder>");
	}

	@Override
	public void delete(RepairOrder t) {
		logger.error("Unavailable operation to entity <RepairOrder>");
        throw new UnsupportedOperationException("Unavailable operation to entity <RepairOrder>");
	}
	

	@Override
	public List<RepairOrder> findAll() throws DaoException {
		
		return null;
	}
	public List<RepairOrder> findAll(int limit, int pageNumber) throws DaoException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement(SQL_REPAIR_ORDER_LIST_SELECT);
			ps.setInt(1, limit);
			int offset = limit * pageNumber - limit;
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();
			List<RepairOrder> listOfRepairOrders = new ArrayList<RepairOrder>();
			while (rs.next()) {
				RepairOrder repairOrder = RepairOrderFactory.getRepairOrderFromResultSet(rs);
				listOfRepairOrders.add(repairOrder);
			}
			return listOfRepairOrders;
		} catch (SQLException e) {
			logger.warn("SQLException while RepairOrderDaoImpl.findAll()", e);
			throw new DaoException("SQLException while RepairOrderDaoImpl.findAll()", e);
		} finally {
			closePreparedStatement(ps);
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}
	public int countAll() throws DaoException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			ps = conn.prepareStatement(SQL_REPAIR_ORDER_COUNT_ALL);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("count(rep_ord_id)");
		} catch (SQLException e) {
			logger.warn("SQLException while RepairOrderDaoImpl.countAll()", e);
			throw new DaoException("SQLException while RepairOrderDaoImpl.countAll()", e);
		} finally {
			closePreparedStatement(ps);
			ConnectionPool.getInstance().releaseConnection(conn);
		}
	}
	public int add(int orderId, int repairCost, String damageDescription) throws DaoException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		try(PreparedStatement psAdd = connection.prepareStatement(SQL_REPAIR_ORDER_ADD);
			PreparedStatement psGet = connection.prepareStatement(SQL_REPAIR_ORDER_FIND_BY_ORDER_ID))
		{
			psAdd.setInt(1, orderId);
			psAdd.setInt(2, repairCost);
			psAdd.setString(3, damageDescription);
			psGet.setInt(1, orderId);
                try (ResultSet rs = psGet.executeQuery()) {
                    if (!rs.next()) {
                        psAdd.executeUpdate();
                        return 1;
                    } else {
                    	return 0;
                    }  
                }
		} catch (SQLException e) {
			logger.error("SQL exception while add new repair order", e);
			throw new DaoException(e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}
	
	public Optional<RepairOrder> findByRepairOrderId (int repairOrderId) throws DaoException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		try(PreparedStatement ps = connection.prepareStatement(SQL_REPAIR_ORDER_FIND_BY_REPAIR_ORDER_ID)) {
			ps.setInt(1, repairOrderId);
			try(ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					RepairOrder repairOrder = RepairOrderFactory.getRepairOrderFromResultSet(rs);
                	return Optional.of(repairOrder);
                } else {
                	return Optional.empty();
                }  
			}
		} catch (SQLException e) {
			logger.error("SQL exception while select repair order by order id", e);
			throw new DaoException(e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}
	
	public void update(int repairCost, String damageDescription, int repairOrderId) throws DaoException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		try(PreparedStatement ps = connection.prepareStatement(SQL_REPAIR_ORDER_UPDATE)){
				ps.setInt(1, repairCost);
				ps.setString(2, damageDescription);
				ps.setInt(3, repairOrderId);
				ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQL exception while update repair order", e);
			throw new DaoException(e);
		} finally {
			ConnectionPool.getInstance().releaseConnection(connection);
		}
	}
}


