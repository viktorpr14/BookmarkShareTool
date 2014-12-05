package org.softserveinc.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.softserveinc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vv on 01.12.2014.
 */
@Repository
@Transactional
public class HibernateDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveUserIntoDB(User user) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User findUserByUsername(String username) {
        List<User> userList =new ArrayList<>();
        userList= sessionFactory.openSession().createQuery("from User where username=?")
                .setParameter(0, username).list();
        if(userList.size()>0)
            return userList.get(0);
        return null;
    }
}
