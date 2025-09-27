package com.logistica.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;
    private static final String URL = "jdbc:h2:./data/logisticaDB;MODE=MySQL;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static synchronized Connection getConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) return conn;
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver H2 no encontrado", e);
        }
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        conn.setAutoCommit(true);
        return conn;
    }
}

