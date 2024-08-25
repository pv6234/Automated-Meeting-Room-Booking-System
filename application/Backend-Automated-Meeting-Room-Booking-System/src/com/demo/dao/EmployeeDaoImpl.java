package com.demo.dao;

import com.demo.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {

    private Connection conn;

    public EmployeeDaoImpl() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to establish a database connection.", e);
        }
    }

    @Override
    public String getEmployeeType(String username) {
        String employeeType = null;
        String query = "SELECT EmployeeType FROM Employee WHERE Username = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employeeType = rs.getString("EmployeeType");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error while fetching employee type: " + e.getMessage());
        }

        return employeeType;
    }
}
