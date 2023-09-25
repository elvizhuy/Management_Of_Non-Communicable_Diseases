package com.devteam.management_of_noncommunicable_diseases.Interface;

public interface SQLException {
    static void printSQLException(java.sql.SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof java.sql.SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((java.sql.SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((java.sql.SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
