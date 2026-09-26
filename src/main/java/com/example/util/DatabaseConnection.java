package com.example.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {

    public static Connection getConnection() throws Exception {

        Properties properties = new Properties();

        InputStream input =
                DatabaseConnection.class
                        .getClassLoader()
                        .getResourceAsStream("db.properties");

        if (input == null) {
            throw new RuntimeException(
                    "db.properties file not found."
            );
        }

        properties.load(input);

        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(
                url,
                username,
                password
        );
    }
}