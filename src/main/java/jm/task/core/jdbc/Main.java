package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        User user = new User("Tom", "Hard", (byte) 22);
        User user2 = new User("Jon", "Smitt", (byte) 35);
        User user3 = new User("Tim", "Ollin", (byte) 25);
        User user4 = new User("Roze", "Bloom", (byte) 45);

//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();
//
//        userDaoJDBC.saveUser(user.getName(), user.getLastName(), user.getAge());
//        userDaoJDBC.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
//        userDaoJDBC.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
//        userDaoJDBC.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
//
//        userDaoJDBC.getAllUsers();
////        userDaoJDBC.removeUserById(2);
////        userDaoJDBC.getAllUsers();
////        userDaoJDBC.cleanUsersTable();
////        userDaoJDBC.dropUsersTable();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser(user.getName(), user.getLastName(), user.getAge());
        userDaoHibernate.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userDaoHibernate.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userDaoHibernate.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        userDaoHibernate.getAllUsers();
//        userDaoHibernate.removeUserById(3);
//        userDaoHibernate.getAllUsers();
//        userDaoHibernate.cleanUsersTable();
//        userDaoHibernate.getAllUsers();
//        userDaoHibernate.dropUsersTable();

    }
}
