package DAO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import entity.Request;
import exception.DaoException;

public interface RequestDAO extends IDAO<Request> {

	Optional<Request> add(entity.Request rentRequest) throws DaoException;

	List<Request> findAll(int recordsPerPage, int listPage) throws DaoException;

	int countAll() throws DaoException;

	void reject(int id, String reason) throws DaoException;

	List<Request> findAllByUserId(int userId, int recordsPerPage, int listPage) throws DaoException;

	int countAllByUserId(int userId) throws DaoException;
	
	boolean deleteById(int id) throws DaoException;
	
	boolean changeDates(int id, Date rentStartDate, Date rentFinishDate) throws DaoException;

	Optional<Request> findById(int requestId)throws DaoException;

}
