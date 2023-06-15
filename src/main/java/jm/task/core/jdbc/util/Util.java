package jm.task.core.jdbc.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/katajdbc";
    private static final String username = "root";
    private static final String password = "root";
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

