package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/carrentprojectdb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "pass";
	private static final String DB_POOL_SIZE = "10";
	private static final ReentrantLock lock = new ReentrantLock();
    private static final AtomicBoolean isInitialized = new AtomicBoolean();
    private static int poolsize;
    private static ConnectionPool instance;
    private final BlockingQueue<Connection> freeConnections;
    private final BlockingQueue<Connection> busyConnections;
    
    private ConnectionPool() {
    	try {
            Class.forName(DB_DRIVER);
            poolsize = Integer.parseInt(DB_POOL_SIZE);
            freeConnections = new ArrayBlockingQueue<>(poolsize);
            busyConnections = new ArrayBlockingQueue<>(poolsize);
            initializeConnectionPool();
        } catch (NullPointerException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void initializeConnectionPool() {
        while (freeConnections.size() < poolsize) {
            try {
                freeConnections.add(DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD));
            } catch (SQLException e) {
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
            	throw new RuntimeException();   // Исправить
            }
            return connection;       
    }
    
    public void releaseConnection(Connection connection) {
            try {
                busyConnections.remove(connection);
                freeConnections.put(connection);
            } catch (InterruptedException e) {
            	throw new RuntimeException();   // Исправить
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