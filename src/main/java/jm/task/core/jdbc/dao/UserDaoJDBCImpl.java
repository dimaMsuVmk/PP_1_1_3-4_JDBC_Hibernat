package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREATE_USERS = """
            CREATE TABLE IF NOT EXISTS Users
            (
                Id BIGINT PRIMARY KEY AUTO_INCREMENT,
                Name VARCHAR(30),
                LastName VARCHAR(30),
                Age TINYINT
            )
            """;
    private static final String SAVE_USER = """
            INSERT INTO Users (Name, LastName, Age)
            VALUES (?, ?, ?);
            """;
    private static final String DROP_USERS = "DROP TABLE IF EXISTS Users";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM Users WHERE ID = ?";
    private static final String SELECT_USERS = "SELECT Id, Name, LastName, Age FROM Users";
    private static final String DELETE = "DELETE FROM Users";

    private Connection connection = null;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }


    @Override
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_USERS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(DROP_USERS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<User> listUsers = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                listUsers.add(user);
            }
            return listUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
