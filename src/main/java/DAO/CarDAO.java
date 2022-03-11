package DAO;

import java.util.List;
import java.util.Optional;

import entity.car.Car;
import exception.DaoException;

public interface CarDAO extends IDAO<Car> {

	Optional<Car> findById(int carId) throws DaoException;

	List<Car> findAll(int limit, int pageNumber) throws DaoException;
	
	List<Car> findAllActive(int limit, int pageNumber) throws DaoException;
	
	int countAll() throws DaoException;

	void changeParameter(int carId, String paramName, String paramValue) throws DaoException;
	
	Optional<Car> add(Car car) throws DaoException;
	
	void changeBlock(int carId, boolean blockStatus) throws DaoException;

}
