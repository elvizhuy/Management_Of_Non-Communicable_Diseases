package com.devteam.management_of_noncommunicable_diseases.Controller;

import java.sql.*;

public class DBConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mon_cda";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Huynn@0908";

    public static Connection open() {
        System.out.println("Connecting database.......");
        try {
            System.out.println("Database connected!");
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!");
            return null;
        }
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            System.out.println("Database connected!");
//            return connection;
//        } catch (SQLException e) {
//            throw new IllegalStateException("Cannot connect the database!", e);
//        }
    }

    public static void closeAll(Connection con, PreparedStatement stm, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}