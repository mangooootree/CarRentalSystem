package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/carrentalservice" +
                                    "?verifyServerCertificate=false" +
                                    "&allowPublicKeyRetrieval=true&useSSL=false" +
                                    "&requireSSL=false" +
                                    "&useLegacyDatetimeCode=false" +
                                    "&amp" +
                                    "&serverTimezone=UTC";
    private static String jdbcUser = "root";
    private static String jdbcPassword = "root";

    public static void init() throws ClassNotFoundException {
        Class.forName(jdbcDriver);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }
}
