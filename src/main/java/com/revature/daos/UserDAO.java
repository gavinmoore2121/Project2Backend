package com.revature.daos;

import com.revature.entities.Pin;
import com.revature.entities.User;

import java.util.List;

/**
 * A database-access object interface to retrieve User objects from the database.
 *
 * Utilizes the Hibernate API and the GenericDao interface to perform CRUD operations and
 * retrieve necessary properties.
 *
 * @author Gavin Moore
 * @version 1.0
 */
public interface UserDAO extends GenericDAO {
    /**
     * Find and return a User by it's unique email.
     * @param email the email of the user to find.
     * @return The User with the given email, or null.
     */
    default User getUserByEmail(String email) {
        return GenericDAO.getEntityByID(User.class, email);
    }

    /**
     * Return all users currently saved in the database.
     * @return A list of all user objects in the database.
     */
    default List<User> getAllUsers() {
        return GenericDAO.getAllEntities(User.class);
    }

    /**
     * Find all pins that the user owns.
     * @param email the email of the user.
     * @return A list of all user pins.
     */
    default List<Pin> getAllUserPinsByEmail(String email) {
        return getAllUserPinsFromUser(getUserByEmail(email));
    }

    /**
     * Find all pins the user owns.
     * @param user the user.
     * @return A list of all user pins.
     */
    default List<Pin> getAllUserPinsFromUser(User user) {
        return user.getUserPins();
    }

    /**
     * Updates all fields of the database entry with the matching email to the properties of the user.
     * @param user the user with updated properties.
     */
    default void updateUser(User user) {
        GenericDAO.update(user);
    }

    /**
     * Delete the user with the matching email.
     * @param user the user to delete.
     */
    default void deleteUser(User user) {
        GenericDAO.delete(user);
    }

    /**
     * Save a user as a new database entry.
     * @param user the user to save.
     */
    default void saveUser(User user) {
        GenericDAO.save(user);
    }

}
