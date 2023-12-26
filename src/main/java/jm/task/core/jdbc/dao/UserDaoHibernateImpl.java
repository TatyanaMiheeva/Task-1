package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {

        try (Session session = Util.getSession()) {
            Transaction transaction = session.beginTransaction();

            String query = "CREATE TABLE IF NOT EXISTS User1 (" +
                    "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "age smallint NOT NULL" + ")";
            session.createSQLQuery(query).executeUpdate();

            transaction.commit();
            System.out.println("Создание таблицы прошло успешно с Hibernate");
        } catch (HibernateException e) {
            System.out.println("Ошибка при создании таблицы users: " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DROP TABLE IF EXISTS User1")) {
            preparedStatement.executeUpdate();
            System.out.println("Удаление таблицы прошло успешно c Hibernate");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        session.beginTransaction();

        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("User с именем - " + name +" добавлен в базу данных с помощью Hibernate");

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);

        session.getTransaction().commit();
        session.close();
        System.out.println("User с id - " + id +" удален из таблицы с помощью Hibernate");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
       Session session = Util.getSession();
       session.beginTransaction();

       users = session.createQuery("FROM User", User.class).getResultList();

        System.out.println(users);

       session.getTransaction().commit();
       session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        session.beginTransaction();

        session.createQuery("DELETE FROM User").executeUpdate();

        System.out.println("Очистили таблицу");
        session.getTransaction().commit();
        session.close();
    }
}
