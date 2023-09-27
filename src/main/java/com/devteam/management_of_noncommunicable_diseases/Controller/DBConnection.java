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
        } catch (java.sql.SQLException e) {
            System.out.println("Cannot connect the database!");
            return null;
        }
    }

    public static void closeAll(Connection con, PreparedStatement stm, ResultSet rs) throws java.sql.SQLException {
        if (rs != null) {
            try {
                rs.close();
            }catch (java.sql.SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (java.sql.SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (java.sql.SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}