package com.revature.daos;

import com.revature.entities.Pin;
import com.revature.entities.User;

import java.util.List;

/**
 * A database-access object interface to retrieve Pin objects from the database.
 *
 * Utilizes the Hibernate API and the GenericDao interface to perform CRUD operations and
 * retrieve necessary properties.
 *
 * @author Gavin Moore
 * @version 1.0
 */
public interface PinDAO extends GenericDAO {
    /**
     * Find and return a Pin by it's ID number.
     * @param id The ID of the desired pin.
     * @return The pin with the given ID, or null.
     */
    static Pin getPinByID(int id) {
        return GenericDAO.getEntityByID(Pin.class, id);
    }

    /**
     * Return all Pins currently saved in the database.
     * @return All pins in the database, or null.
     */
    static List<Pin> getAllPins() {
        return GenericDAO.getAllEntities(Pin.class);
    }

    /**
     * Find the User who owns the given pin.
     * @param pin The pin to find the owner of.
     * @return The owner of the pin, or null.
     */
    static User getPinOwnerFromPin(Pin pin) {
        return pin.getOwner();
    }

    /**
     * Find the User who owns the pin with the given ID.
     * @param id The ID number of the pin.
     * @return The User who owns the pin, or null.
     */
    static User getPinOwnerByPinID(int id) {
        return getPinOwnerFromPin(getPinByID(id));
    }

    /**
     * Update a pin saved within the database. Alters all database fields of the pin with the matching
     * ID number to the properties of the given pin.
     * @param pin The pin to update, with new properties saved.
     */
    static void updatePin(Pin pin) {
        GenericDAO.update(pin);
    }

    /**
     * Deletes the given pin from the database.
     * @param pin the pin to delete.
     */
    static void deletePin(Pin pin) {
        GenericDAO.delete(pin);
    }

    /**
     * Saves a pin to the database.
     * @param pin the pin to save.
     */
    static void savePin(Pin pin) {
        GenericDAO.save(pin);
    }


}
