package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static int foo(){
        throw new NullPointerException();
    }
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Dima", "Popov", Byte.valueOf("9"));
        userService.saveUser("Dima", "Ivanov", Byte.valueOf("12"));
        userService.saveUser("Oleg", "Kolov", Byte.valueOf("19"));
        for (User u : userService.getAllUsers()) {
            System.out.println(u);
        }
        userService.removeUserById(2);
        System.out.println("Users after remove with id = 2");
        for (User u : userService.getAllUsers()) {
            System.out.println(u);
        }
        userService.cleanUsersTable();
    }
}
