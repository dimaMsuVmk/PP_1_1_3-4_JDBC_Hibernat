package jm.task.core.jdbc.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/katajdbc";
        String username = "root";
        String password = "root";
        //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        return DriverManager.getConnection(url, username, password);
    }
}

