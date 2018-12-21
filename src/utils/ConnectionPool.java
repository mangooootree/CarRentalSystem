package utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger log = LogManager.getLogger(ConnectionPool.class.getName());
    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
    }

     Connection getConnection() throws PoolException {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            log.fatal("Can't establish connection to the db. " + e);
            throw new PoolException(e);
        }
        log.debug("Getting connection. Remains " + connections.size());
        return connection;
    }

    void freeConnection(Connection connection) throws PoolException {
        try {
            if (connection != null) {
                connections.put(connection);
            }
        } catch (InterruptedException e) {
            log.error("Exception while returning the connection. " + e);
            throw new PoolException(e);
        }
    }

    public void init(int minSize, int maxSize) throws PoolException {
        connections = new ArrayBlockingQueue<>(maxSize);
        try {
            if (minSize <= maxSize) {
                for (int i = 0; i < minSize; i++) {
                    connections.add(establishConnection());
                    log.debug("Adding connection");
                }
            }
            log.debug("Pool is full");
        } catch (SQLException e) {
            throw new PoolException(e);
        }
    }


    public void destroy() {
        synchronized (connections) {
            synchronized (connections) {
                connections.stream().filter((connection) -> (connection != null)).forEach((connection) -> {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        log.error("Can't close connection (" + connection + "): ", e);
                    }
                });
            }
        }
    }

    private Connection establishConnection() throws SQLException {
        return new PooledConnection(Connector.getConnection());
    }


    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }
}