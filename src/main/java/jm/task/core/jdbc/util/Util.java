package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/katajdbc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory != null) {
            return sessionFactory;
        }
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, DRIVER_NAME);
        properties.put(Environment.URL, URL);
        properties.put(Environment.USER, USERNAME);
        properties.put(Environment.PASS, PASSWORD);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        sessionFactory = new Configuration()
                    .setProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        return sessionFactory;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

