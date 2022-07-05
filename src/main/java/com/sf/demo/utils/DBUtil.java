package com.sf.demo.utils;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    @Value("{spring.datasource.url}")
    private String url;
    @Value("{spring.datasource.username}")
    private String username;
    @Value("{spring.datasource.password}")
    private String password;

    private DBUtil instance;

    private DBUtil() {
    }

    public DBUtil getInstance() {
        if (instance == null) {
            instance = new DBUtil();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
