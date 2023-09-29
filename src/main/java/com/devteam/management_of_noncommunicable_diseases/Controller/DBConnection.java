package com.devteam.management_of_noncommunicable_diseases.Controller;

import java.sql.*;

public class DBConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mon_cda";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Huynn@0908";

    private static Connection connection = null;
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
            } catch (java.sql.SQLException e) {
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

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;

        try {
            //Connect to DB (Establish Oracle Connection)
            open();
            System.out.println("Select statement: " + queryStmt + "\n");
            //Create statement
            stmt = connection.createStatement();
            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

        } catch (SQLException e) {
            System.out.println("Lỗi khi thực thi lệnh : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            closeAll(connection, null, resultSet);
        }
        return resultSet;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            open();
            //Create Statement
            stmt = connection.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Lỗi khi thực thi lệnh : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            closeAll(connection, null, null);
        }
    }
}