
package listener;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utils.ConnectionPool;
import utils.Connector;
import utils.PoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(ContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Connector.init();
        }
        catch (ClassNotFoundException e){
        }

        try {
            log.info("Initializing pool");
            ConnectionPool.getInstance().init(5,6);
        } catch (PoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
    }

}