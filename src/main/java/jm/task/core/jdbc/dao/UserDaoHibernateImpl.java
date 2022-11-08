package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.util.List;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.createNativeQuery("create table IF NOT EXISTS users(id int NOT NULL AUTO_INCREMENT," +
                "name varchar(45) NOT NULL," +
                "lastname varchar(45) NOT NULL," +
                "age int NOT NULL," +
                "primary key (id))", User.class).executeUpdate();
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.createNativeQuery("drop table if exists users", User.class).executeUpdate();
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(new User(name, lastName, age));

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        User user = session.get(User.class, id);
        session.remove(user);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<User> listUsers = new ArrayList<>();
        listUsers = session.createQuery("from User").getResultList();

        session.getTransaction().commit();

        session.close();

        return listUsers;
    }

    @Override
    public void cleanUsersTable() {
        Session session = util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.createNativeQuery("TRUNCATE TABLE Users", User.class).executeUpdate();

        session.getTransaction().commit();

        session.close();
    }
}
