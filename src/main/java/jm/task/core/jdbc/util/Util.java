package jm.task.core.jdbc.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    public static void main(String[] args) {
//        Long id
//        String name
//        String lastName
//        Byte age
        String query = """
                CREATE TABLE IF NOT EXISTS Users
                (
                    Id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    Name VARCHAR(30),
                    LastName VARCHAR(30),
                    Age TINYINT
                )
                """;
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/katajdbc";
        String username = "root";
        String password = "root";
        //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        return DriverManager.getConnection(url, username, password);
    }
}

