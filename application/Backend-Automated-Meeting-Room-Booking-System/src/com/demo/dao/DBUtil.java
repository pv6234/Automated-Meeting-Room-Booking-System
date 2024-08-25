package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
    private static Connection conn=null;
    public static Connection getMyConnection(){
        try {
            if (conn == null) {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
                //step 2
                conn = DriverManager.getConnection(url, "root", "root");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeMyConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}