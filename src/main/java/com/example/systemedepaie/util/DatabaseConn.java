package com.example.systemedepaie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {
    private static final String URL = "jdbc:mysql://localhost:3306/systemepaie";
    private static final String USER = "root"; // Remplacez par votre nom d'utilisateur MySQL
    private static final String PASSWORD = "root"; // Remplacez par votre mot de passe MySQL

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
