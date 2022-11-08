package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    // Коннект JDBC
    private final String URL = "jdbc:mysql://localhost:3306/databasetest";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private Connection connection;
    Driver driver;

    public Util() {
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Подключение к базе установлено");
        } catch (SQLException e) {
            System.out.println("Соединение не установлено");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    // Конфигурация Hibernate
    Configuration configuration = new Configuration().addAnnotatedClass(jm.task.core.jdbc.model.User.class);
    private SessionFactory sessionFactory = configuration.buildSessionFactory();
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
