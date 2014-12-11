package org.softserveinc.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.softserveinc.domain.Community;
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveUserIntoDB(User user) {
//        Session session = getSessionFactory().openSession();
//        session.beginTransaction();
//        session.save(user);
//        session.getTransaction().commit();
//        session.close();

        Session session = getSessionFactory().getCurrentSession();
        session.save(user);
    }

    public void saveCommunityIntoDB(Community community) {
//        Session session = getSessionFactory().openSession();
//        session.beginTransaction();
//        session.save(community);
//        session.getTransaction().commit();
//        session.close();

        Session session = getSessionFactory().getCurrentSession();
        session.save(community);
    }

    public User findUserByUsername(String username) {
//        List<User> userList =new ArrayList<User>();
//        userList= sessionFactory.openSession().createQuery("from User where username=?")
//                .setParameter(0, username).list();
//        if(userList.size()>0)
//            return userList.get(0);
//        return null;

//        Session session = getSessionFactory().openSession();
//        session.beginTransaction();
//
//        Query query = session.createQuery("from User where username=?").setParameter(0, username);
//        List<User> usersList = query.list();
//
//        session.getTransaction().commit();
//        session.close();
//
//        if(usersList.size() > 0) {
//            return usersList.get(0);
//        }
//        return null;

        Session session = getSessionFactory().getCurrentSession();

        Query query = session.createQuery("from User where username=?").setParameter(0, username);
        List<User> usersList = query.list();

        if(usersList.size() > 0) {
            return usersList.get(0);
        }
        return null;

    }

    public void updateUserInDB(User user) {
//        Session session = getSessionFactory().openSession();
//        session.beginTransaction();
//        session.update(user);
//        session.getTransaction().commit();
//        session.close();

        Session session = getSessionFactory().getCurrentSession();
        session.update(user);
    }

    public User findUserByUsername(String username) {
        List<User> userList = sessionFactory.openSession().createQuery("from User where username=?")
                                            .setParameter(0, username).list();
        if(userList.size()>0)
            return userList.get(0);
        return null;
    }
}
