package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.createNativeQuery("create table IF NOT EXISTS users(id int NOT NULL AUTO_INCREMENT," +
                "name varchar(45) NOT NULL," +
                "lastname varchar(45) NOT NULL," +
                "age int NOT NULL," +
                "primary key (id))", User.class).executeUpdate();

        session.save(new User("Maxim", "Alexandrovich", (byte) 19));

        session.getTransaction().commit();

        session.close();

        /*
        SessionFactory sf = util.getConfiguration().buildSessionFactory();
        Session session = sf.getCurrentSession();
        session.beginTransaction();

        session.createNativeQuery("drop table if exists users", User.class).executeUpdate();
        session.getTransaction().commit();

        sf.close();
        */

    }
}
