package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class ConnectionPool {
	private static final Logger logger = Logger.getLogger(ConnectionPool.class);
	
	private static final String DB_PROPERTY_FILE_NAME = "db";
	private static final String DB_DRIVER_KEY = "driver";
	private static final String DB_URL_KEY = "url";
	private static final String DB_USER_KEY = "user";
	private static final String DB_PASSWORD_KEY = "password";
	private static final String DB_DRIVER;
	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PASSWORD;
	private static final int DB_POOL_SIZE = 10;
	private static final ReentrantLock lock = new ReentrantLock();
	private static final AtomicBoolean isInitialized = new AtomicBoolean();
	private static ConnectionPool instance;
	private final BlockingQueue<Connection> freeConnections;
	private final BlockingQueue<Connection> busyConnections;

	static {
    	try {
    		ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_PROPERTY_FILE_NAME);
    		DB_URL = resourceBundle.getString(DB_URL_KEY);
    		DB_USER = resourceBundle.getString(DB_USER_KEY);
    		DB_PASSWORD = resourceBundle.getString(DB_PASSWORD_KEY);
    		DB_DRIVER = resourceBundle.getString(DB_DRIVER_KEY);
    		Class.forName(DB_DRIVER);
    		}
    	catch (MissingResourceException e) {
    		logger.fatal("Property file not found or has incorrect data " + DB_PROPERTY_FILE_NAME);
    		throw new RuntimeException("Property file not found or has incorrect data " + DB_PROPERTY_FILE_NAME, e);
    	} catch (ClassNotFoundException e) {
    		logger.fatal("Driver not found " + DB_PROPERTY_FILE_NAME);
    		throw new RuntimeException("Driver not found " + DB_PROPERTY_FILE_NAME, e);
    	}
    	}
  
	
    private ConnectionPool() {
    	try {
            Class.forName(DB_DRIVER);
            freeConnections = new ArrayBlockingQueue<>(DB_POOL_SIZE);
            busyConnections = new ArrayBlockingQueue<>(DB_POOL_SIZE);
            initializeConnectionPool();
        } catch (NullPointerException | ClassNotFoundException e) {
        	logger.fatal("Connection pool have not been created", e);
            throw new RuntimeException(e);
        }
    }
    
    private void initializeConnectionPool() {
        while (freeConnections.size() < DB_POOL_SIZE) {
            try {
                freeConnections.add(DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD));
            } catch (SQLException e) {
            	logger.error("Connection pool initialization error", e);
                throw new RuntimeException();
            }
        }
    }
    
    public Connection getConnection() {
        Connection connection = null;
            try {
                connection = freeConnections.take();
                busyConnections.put(connection);   
            } catch (InterruptedException e) {
            	logger.error("Error while take connection from pool.", e);
                Thread.currentThread().interrupt();
            }
            return connection;       
    }
    
    public void releaseConnection(Connection connection) {
            try {
                busyConnections.remove(connection);
                freeConnections.put(connection);
            } catch (InterruptedException e) {
            	logger.error("Error while put connection back to pool.", e);
                Thread.currentThread().interrupt();
            }
    }
    
    public static ConnectionPool getInstance() {
        if (!isInitialized.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isInitialized.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
    
	
}