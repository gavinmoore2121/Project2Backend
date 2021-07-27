package com.revature.daos;


import com.revature.utilties.hibernate.HibernateUtil;
import com.revature.utilties.log4j.LoggerTools;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

/**
 * Generic Data Access Object interface.
 *
 * Contains static methods to perform CRUD operations on generic entities in a database using the Hibernate API.
 * @author Gavin Moore
 * @version 1.0
 */
public interface GenericDAO {
    Logger logger = LoggerTools.getAndConfigureLogger(null);

    /**
     * Retrieve all entities of the given class in the database.
     * @param type The class of entity to retrieve.
     * @return A list of all entities of the given type stored in the database.
     */
    static <T> List<T> getAllEntities(Class<T> type) {
        logger.info("Getting all entities");
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaQuery<T> criteria = session.getCriteriaBuilder().createQuery(type);
        criteria.from(type);
        List<T> result = session.createQuery(criteria).getResultList();
        session.close();
        return result;
    }

    /**
     * Retrieve an entity by their unique ID.
     * @param type The class of entity to retrieve.
     * @param uniqueID The serializable, primary key of the entity.
     * @return The entity with the given primary key.
     */
    static <T, ID extends Serializable> T getEntityByID(Class<T> type, ID uniqueID) {
        logger.info("Getting entity by ID: " + uniqueID);
        Session session = HibernateUtil.getSessionFactory().openSession();
        T result = session.get(type, uniqueID);
        session.close();
        return result;
    }

    /**
     * Save an entity to the database.
     * @param entity The entity to save, defined in javax.persistence.
     */
    static <T> void save(T entity) {
        logger.info("Saving entity.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        session.save(entity);
        trans.commit();
        session.close();
    }

    /**
     * Update an existing entity within a database.
     * @param entity The pre-existing entity to update, defined in javax.persistence.
     */
    static <T> void update(T entity) {
        logger.info("Updating entity.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        session.update(entity);
        trans.commit();
        session.close();
    }

    /**
     * Delete an existing entity from the database.
     * @param entity The pre-existing entity to delete, defined in javax.persistence.
     */
    static <T> void delete(T entity) {
        logger.info("Deleting entity.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        session.delete(entity);
        trans.commit();
        session.close();
    }

}
