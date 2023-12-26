package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    protected   static final String URL = "jdbc:postgresql://localhost:5433/postgres";

    protected static final String USERNAME = "postgres";
    protected static  final String PASSWORD = "postgres";
    protected static Connection connection;

    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static Session getSession() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
       SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory.openSession();

    }
}
