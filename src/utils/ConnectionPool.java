package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConnectionPool {
    private int maxSize;
    private Queue<Connection> freeConnections = new ConcurrentLinkedQueue<>();
    private Set<Connection> usedConnections = new ConcurrentSkipListSet<>();

    private ConnectionPool() {
    }

    public Connection getConnection() throws PoolException {
        Connection connection = null;
        while (connection == null) {
            try {
                connection = freeConnections.poll();
                if (connection != null) {
                    close(connection);
                    connection = null;
                } else if (maxSize == 0 || usedConnections.size() < maxSize) {
                    connection = establishConnection();
                } else {
                    throw new PoolException("maxSize exceeded");
                }
            } catch (SQLException e) {
                throw new PoolException(e);
            }
        }
        usedConnections.add(connection);
        return connection;
    }

    void freeConnection(Connection connection) throws SQLException {
        usedConnections.remove(connection);
        freeConnections.add(connection);
    }

    public void init(int minSize, int maxSize) throws PoolException {
        try {
            if (minSize <= maxSize) {
                for (int i = 0; i < minSize; i++) {
                    freeConnections.add(establishConnection());
                }
                this.maxSize = maxSize;
            } else {
                throw new PoolException("minSize is to be less or equal maxSize");
            }
        } catch (SQLException e) {
            throw new PoolException(e);
        }
    }


    public void destroy() {
        synchronized (usedConnections) {
            synchronized (freeConnections) {
                usedConnections.addAll(freeConnections);
                freeConnections.clear();
                for (Connection connection : usedConnections) {
                    close(connection);
                }
                usedConnections.clear();
            }
        }
    }

    private Connection establishConnection() throws SQLException {
        return new PooledConnection(Connector.getConnection());
    }

    private void close(Connection connection) {
            synchronized (connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }
}