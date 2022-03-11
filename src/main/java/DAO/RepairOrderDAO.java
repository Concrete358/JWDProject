package DAO;

import java.util.List;
import java.util.Optional;

import entity.RepairOrder;
import exception.DaoException;

public interface RepairOrderDAO extends IDAO<RepairOrder> {

	List<RepairOrder> findAll(int recordsPerPage, int listPage) throws DaoException;

	int countAll() throws DaoException;

	int add(int orderId, int repairCost, String damageDescription) throws DaoException;
	
	Optional<RepairOrder> findByRepairOrderId (int orderId) throws DaoException;
	
	void update(int repairCost, String damageDescription, int repairOrderId) throws DaoException;

}
