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
    private static final String REMOVE_USER_BY_ID = "DELETE FROM User u WHERE u.id=:id";
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
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(CREATE).executeUpdate();
            transaction.commit();
        }
    }

    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(DROP_USERS).executeUpdate();
            transaction.commit();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("error in sql query");
            }
            System.out.println("error initializing: transaction == null");
        }
    }

    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(REMOVE_USER_BY_ID)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("error in sql query");
            }
            System.out.println("error initializing: transaction == null");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = null;
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createSQLQuery(SELECT_USERS).addEntity(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("error in sql query");
            }
            System.out.println("error initializing: transaction == null");
        }
        return list;
    }

    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(DELETE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("error in sql query");
            }
            System.out.println("error initializing: transaction == null");
        }
    }
}
