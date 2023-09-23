package com.devteam.management_of_noncommunicable_diseases.Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcDaoLoginRegister implements SQLException{
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public boolean validate(String username, String password,String QUERY) throws java.sql.SQLException {
//      try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        try {
            connection = DBConnection.open();
            assert connection != null;
            preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (java.sql.SQLException e) {
            SQLException.printSQLException(e);
        } finally {
            DBConnection.closeAll(connection, preparedStatement, resultSet);
        }
        return false;
    }

    public boolean validateDuplicateName(String username,String QUERY) throws java.sql.SQLException {
        String existingName;
        try {
            connection = DBConnection.open();
            assert connection != null;
            preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                existingName = resultSet.getString("user_name");
                if (username.equals(existingName)) {
                    System.out.println("Tên đã tồn tại: " + existingName);
                    return false;
                }
            }
        } catch (java.sql.SQLException e) {
            SQLException.printSQLException(e);
        } finally {
            DBConnection.closeAll(connection, preparedStatement, resultSet);
        }
        return true;
    }
    public void insertRecord (String username, String password,String QUERY) throws java.sql.SQLException {
        try {
            connection = DBConnection.open();
            assert connection != null;
            preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();

        } catch (java.sql.SQLException e) {
            SQLException.printSQLException(e);
        } finally {
            DBConnection.closeAll(connection, preparedStatement, resultSet);
        }
    }

}
