package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement =
                     connection.createStatement()) {

            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS User1 (" +
                    "id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "age smallint NOT NULL" + ")""");
            System.out.println("Создание таблицы прошло успешно ");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы users: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement =
                     connection.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS User1");
            System.out.println("Удаление таблицы прошло успешно ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO User1 (name, lastName, age) VALUES (?,?,?)")){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name +" добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении user в таблицу");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM User1 WHERE id = ?")){

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate(); // выполнение на бд

            System.out.println("Удаление из таблицы по id прошло успешно ");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления user по id из таблицы");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM User1")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);
                users.add(user);
                System.out.println(user);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка, не получилось получить список user");
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){

            statement.executeUpdate("DELETE FROM User1");

            System.out.println("Удаление из таблицы прошло успешно ");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления данных из таблицы");
        }
    }
}
