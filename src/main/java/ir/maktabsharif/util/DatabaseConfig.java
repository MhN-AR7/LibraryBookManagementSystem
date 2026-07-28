package ir.maktabsharif.util;

import ir.maktabsharif.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/hw19", "postgres", "2117");
        }
        catch (SQLException e) {
            throw new DatabaseConnectionException("Connection to Database Failed: " + e.getMessage());
        }
    }
}
