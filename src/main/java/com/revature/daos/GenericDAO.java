package com.revature.daos;


import com.revature.utilties.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

public interface GenericDAO {

    static <T> List<T> getAllEntities(Class<T> type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaQuery<T> criteria = session.getCriteriaBuilder().createQuery(type);
        criteria.from(type);
        List<T> result = session.createQuery(criteria).getResultList();
        session.close();
        return result;
    }

    static <T, ID extends Serializable> T getEntity(Class<T> type, ID uniqueID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T result = session.get(type, uniqueID);
        session.close();
        return result;
    }

    static <T> void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        session.save(entity);
        trans.commit();
        session.close();
    }

    static <T> void update(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        session.update(entity);
        trans.commit();
        session.close();
    }
    
    static <T> void delete(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        session.delete(entity);
        trans.commit();
        session.close();
    }

}
