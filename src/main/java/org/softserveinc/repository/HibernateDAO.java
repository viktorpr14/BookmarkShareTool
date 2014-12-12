package org.softserveinc.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.softserveinc.domain.Team;
import org.softserveinc.domain.User;
import org.softserveinc.domain.UserTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        Session session = getSessionFactory().getCurrentSession();
        session.save(user);
    }

    public void saveTeamIntoDB(Team team) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(team);
    }

    public User findUserByUsername(String username) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from User where username=?").setParameter(0, username);
        List<User> usersList = query.list();

        if(usersList.size() > 0) {
            return usersList.get(0);
        }
        return null;
    }

    public void updateUserInDB(User user) {
        Session session = getSessionFactory().getCurrentSession();
        session.update(user);
    }

    public void saveUserTeamIntoDB(UserTeam userTeam) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(userTeam);
    }
}
