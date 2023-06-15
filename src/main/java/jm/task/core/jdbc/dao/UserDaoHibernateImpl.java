package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String DROP_USERS = "DROP TABLE IF EXISTS users";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE ID = ?";
    private static final String SELECT_USERS = "SELECT * FROM users";
    private static final String DELETE = "DELETE FROM users";
    private static final String CREATE = "CREATE TABLE IF NOT EXISTS users (" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(50) NOT NULL," +
            "lastName VARCHAR(50) NOT NULL," +
            "age TINYINT NOT NULL" +
            ")";
    public UserDaoHibernateImpl() {
    }

    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(CREATE);
            query.executeUpdate();
            transaction.commit();
        }
    }

    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(DROP_USERS).executeUpdate();
            transaction.commit();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.save(new User(name, lastName, age));
            t.commit();
        }
    }

    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User u where u.id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            list = session.createSQLQuery(SELECT_USERS).addEntity(User.class).list();
            transaction.commit();
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(DELETE).executeUpdate();
            transaction.commit();
        }
    }
}
