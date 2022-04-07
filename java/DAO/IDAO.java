package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import exception.DaoException;

public interface IDAO<T> {
	
	void create(T t);
	T read(T t);
	void update(T t);
	void delete(T t);
	List<T> findAll() throws DaoException;
	
	default void closePreparedStatement(PreparedStatement ps) throws DaoException {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException while closing PreparedStatement in OrderDaoImpl", e);
        }
    }

	

}
