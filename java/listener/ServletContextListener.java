package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import connectionPool.ConnectionPool;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
	Logger logger = Logger.getLogger(ServletContextListener.class);

    public void contextInitialized(ServletContextEvent sce)  {
    	new DOMConfigurator().doConfigure("E:\\Java Web Development (Study)\\JWDproject\\src\\main\\resources/log4j.xml", LogManager.getLoggerRepository());
    	logger.info("logger configured");
    	ConnectionPool.getInstance();
    	logger.info("ConnectionPool intialized");
    }
    
    public void contextDestroyed(ServletContextEvent sce)  {}
	
}