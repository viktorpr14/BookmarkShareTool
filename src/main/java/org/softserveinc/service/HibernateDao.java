package org.softserveinc.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.softserveinc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by vv on 01.12.2014.
 */
@Repository
public class HibernateDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveUserIntoDB(User user) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
