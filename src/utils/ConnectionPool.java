package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;


public final class ConnectionPool {

    public static ConnectionPool INSTANCE;
    private static ReentrantLock lock;
    private BlockingQueue<Connection> connections;
    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/carrentalservice" +
                                                "?verifyServerCertificate=false"+
                                                "&useSSL=false"+
                                                "&requireSSL=false"+
                                                "&useLegacyDatetimeCode=false"+
                                                "&amp"+
                                                "&serverTimezone=UTC";
    private final static String DATABASE_LOGIN = "root";
    private final static String DATABASE_PASSWORD = "admin";
    private final static int CONNECTIONS_QUANTITY = 10;
    private final static String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";

    private ConnectionPool() {
        Connection connection;
            try {
                connections = new ArrayBlockingQueue<>(CONNECTIONS_QUANTITY);
                Class.forName(DATABASE_DRIVER).newInstance();

                for (int i = 0; i < CONNECTIONS_QUANTITY; i++) {
                    connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
                    System.out.println("drivermanager has created new connection just now");
                    if (connection != null) {
                        connections.put(connection);
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {

            } catch (InstantiationException | IllegalAccessException | InterruptedException ex) {

            }
    }

    public static ConnectionPool getInstance() {

        lock = new ReentrantLock();
        if (INSTANCE == null) {
            lock.lock();
            try { 
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                    System.out.println("pool has created");
                }
            } finally {
                lock.unlock();
            }
        }
        return INSTANCE;
    }
    public Connection getConnection() throws SQLException {

        Connection connection = null;
        try {
            connection = connections.take();
            System.out.println("connection has taken");
            if (connection == null) {
                throw new SQLException();
            }
        } catch (InterruptedException ex) {

        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        try {
            if (connection != null) {
                connections.put(connection);
            }
        } catch (InterruptedException ex) {
        }
    }

    public static void releaseConnectionPool() {
        if (INSTANCE != null) {
            lock.lock();
            try {
                if (INSTANCE != null) {
                    INSTANCE.connections.stream().filter((connection) -> (connection != null)).forEach((connection) -> {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                        }
                    });
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
