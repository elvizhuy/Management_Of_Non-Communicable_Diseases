package com.devteam.management_of_noncommunicable_diseases.Controller;

import com.devteam.management_of_noncommunicable_diseases.Interface.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class JdbcDaoLoginRegister implements SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    MD5 md5 = new MD5();
    public boolean validate(String username, String password,String QUERY) throws java.sql.SQLException {

        try {
            connection = DBConnection.open();
            assert connection != null;
            preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            String encodedPassword = md5.encode(password);
            while (resultSet.next()) {
                String passwordInDB = resultSet.getString("password");
                if (!Objects.equals(passwordInDB,encodedPassword)){
                    return true;
                }
            }

        } catch (java.sql.SQLException e) {
            SQLException.printSQLException(e);
        } finally {
            DBConnection.closeAll(connection, preparedStatement, resultSet);
        }
        return false;
    }

    public boolean validateDuplicatedName(String username,String QUERY) throws java.sql.SQLException {
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
            preparedStatement.executeUpdate();

        } catch (java.sql.SQLException e) {
            SQLException.printSQLException(e);
        } finally {
            DBConnection.closeAll(connection, preparedStatement, resultSet);
        }
    }

}
