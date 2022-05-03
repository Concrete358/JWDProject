package connectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager implements AutoCloseable {
	
	private Connection internalConnection;
	
	public ConnectionManager() {
		internalConnection = ConnectionPool.getInstance().getConnection();
	}
	
	public Connection getConnection() {
		return internalConnection;
	}
		
	@Override
	public void close() throws SQLException {
		ConnectionPool.getInstance().releaseConnection(internalConnection);
	}

}
