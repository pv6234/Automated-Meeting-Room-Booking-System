package com.demo.dao;

import com.demo.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerCreditsDaoImpl implements ManagerCreditsDao{

    private Connection conn;

    public ManagerCreditsDaoImpl() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to establish a database connection.", e);
        }
    }

    public void resetAllManagerCredits(int credits) {
        String query = "UPDATE managercredits SET credits = ?";

        try (Connection conn = DBUtil.getMyConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, credits);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Failed to reset manager credits: " + e.getMessage());
        }
    }

    @Override
    public void updateCredits(int mgrCredits, String username) {
        String sql = "update managercredits set credits = ? where name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, mgrCredits);
            pstmt.setString(2, username);

            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public int getMgrCredits(String username) {
        int credits = 0;
        String sql = "SELECT credits FROM mgrcredit WHERE name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    credits = rs.getInt("credits");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return credits;
    }
}
