package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        daoJDBC.dropUsersTable();
        daoJDBC.createUsersTable();
        daoJDBC.saveUser("Dima", "Popov", Byte.valueOf("9"));
        daoJDBC.saveUser("Dima", "Ivanov", Byte.valueOf("12"));
        daoJDBC.saveUser("Oleg", "Kolov", Byte.valueOf("19"));
        for (User u : daoJDBC.getAllUsers()) {
            System.out.println(u);
        }
        daoJDBC.removeUserById(2);
        System.out.println("Users after remove with id = 2");
        for (User u : daoJDBC.getAllUsers()) {
            System.out.println(u);
        }
        //daoJDBC.cleanUsersTable();

    }
}
