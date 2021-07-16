package com.revature.daos;

import com.revature.entities.User;
import com.revature.utilties.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Class containing controls for CRUD operations on the user table.
 * Operates via the Hibernate API.
 *
 * @author Gavin Moore
 * @version 1.0
 */
public class UserDAO {
    /**
     * Retrieve a user based on their unique email.
     * @param email the email the user registered their account with.
     * @return The user with the provided email.
     */
    public static User getUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, email);
        session.close();
        return user;
    }


    public static List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createCriteria(User.class);
        session.close();
        return user;
    }
}
