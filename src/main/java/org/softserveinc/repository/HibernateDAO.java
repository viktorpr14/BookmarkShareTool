package org.softserveinc.repository;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.softserveinc.domain.*;
import org.softserveinc.util.ReferenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

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
        List<User> usersList = (List<User>) query.list();

        if(usersList.size() > 0) {
            return usersList.get(0);
        }
        return null;
    }

    public void updateUserInDB(User user) {
        Session session = getSessionFactory().getCurrentSession();
        session.update(user);
    }

    public void updateUserTeamInDB(UserTeam userTeam) {
        Session session = getSessionFactory().getCurrentSession();
        session.update(userTeam);
    }

    public void saveUserTeamIntoDB(UserTeam userTeam) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(userTeam);
    }

    public List<User> getUsersByUserIds(Collection<Integer> userIds) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.in("userId", userIds));

        List<User> users = (List<User>) criteria.list();

        return users;
    }

    public List<User> getUsersByEmail(String email) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));

        List<User> users = (List<User>) criteria.list();

        return users;
    }

    public List<Team> getTeamsByTeamIds(List<Integer> teamsIds) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Team.class);
        criteria.add(Restrictions.in("teamId", teamsIds));

        List<Team> teams = (List<Team>) criteria.list();

        return teams;
    }

    public Team getTeamById(String teamId) {
        Session session = getSessionFactory().getCurrentSession();
        Team team = (Team) session.get(Team.class, Integer.parseInt(teamId));
        return team;
    }

    public User getUserById(String localUserId) {
        Session session = getSessionFactory().getCurrentSession();
        User user = (User) session.get(User.class, Integer.parseInt(localUserId));
        return user;
    }

    public List<User> getUsersExceptGivenUserIds(Set<Integer> userIds) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);

        criteria.add(Restrictions.not(Restrictions.in("userId", userIds)));

        List<User> users = (List<User>) criteria.list();

        return users;
    }

    public List<UserTeam> findUserTeamsWhereUserIsMemberOrOwnerByUserId(Integer userId) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(UserTeam.class);

        Criterion criterion1 = Restrictions.eq("userId", userId);
        Criterion criterion2 = Restrictions.in("status", Arrays.asList("owner", "accepted"));

        criteria.add(Restrictions.and(criterion1, criterion2));

        List<UserTeam> userTeams = (List<UserTeam>) criteria.list();

        return userTeams;
    }

    public List<UserTeam> getUserTeamsByTeamId(Integer teamId) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(UserTeam.class);
        criteria.add(Restrictions.eq("teamId", teamId));

        List<UserTeam> userTeams = (List<UserTeam>) criteria.list();

        return userTeams;
    }

    public List<UserTeam> getUserTeamsWithInvitationsOnlyByUserId(Integer userId) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(UserTeam.class);
        criteria.add(Restrictions.and(Restrictions.eq("userId", userId), Restrictions.eq("status", "invited")));

        List<UserTeam> userTeams = (List<UserTeam>) criteria.list();

        return  userTeams;
    }

    public UserTeam getUserTeamById(String userTeamId) {
        Session session = getSessionFactory().getCurrentSession();
        UserTeam userTeam = (UserTeam) session.get(UserTeam.class, Integer.parseInt(userTeamId));
        return userTeam;
    }

    public Integer saveBookmarkIntoDB(Bookmark bookmark){
        Session session = getSessionFactory().getCurrentSession();
       return (Integer)session.save(bookmark);

    }

    public List<Bookmark> getBookmarksByTeamId(String teamId) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Bookmark.class);
        criteria.add(Restrictions.eq("teamId", Integer.parseInt(teamId)));

        List<Bookmark> bookmarks = (List<Bookmark>) criteria.list();

        return bookmarks;
    }

    public List<Bookmark> getBookmarksByUserId(String userId) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Bookmark.class);
        criteria.add(Restrictions.eq("userId", Integer.parseInt(userId)));

        List<Bookmark> bookmarks = (List<Bookmark>) criteria.list();

        return bookmarks;
    }

    public List<BookmarkReference> getReferenceByTeamId(String teamId) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(BookmarkReference.class);
        criteria.add(Restrictions.and(Restrictions.eq("referenceType", ReferenceType.TEAM),Restrictions.eq("referenceId", Integer.parseInt(teamId)) ));
        List<BookmarkReference> bookmarkRefs = (List<BookmarkReference>) criteria.list();
        return bookmarkRefs;
    }

    public List<Bookmark> getBookmarksByIds(Set<Integer> bookmarkIds) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Bookmark.class);
        criteria.add(Restrictions.in("bookmarkId", bookmarkIds));
        return (List<Bookmark>) criteria.list();

    }

    public List<BookmarkReference> getReferenceByUserId(String userId) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(BookmarkReference.class);
        criteria.add(Restrictions.and(Restrictions.eq("referenceType", ReferenceType.USER),Restrictions.eq("referenceId", Integer.parseInt(userId)) ));
        List<BookmarkReference> bookmarkRefs = (List<BookmarkReference>) criteria.list();
        return bookmarkRefs;
    }

    public void saveBookmarkReference(BookmarkReference bookmarkReference) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(bookmarkReference);
    }

    public void saveProviderUserLocalUser(ProviderUserLocalUser providerUserLocalUser) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(providerUserLocalUser);
    }

    public void updateProviderUserLocalUser(ProviderUserLocalUser providerUserLocalUser) {
        Session session = getSessionFactory().getCurrentSession();
        session.update(providerUserLocalUser);
    }

    public ProviderUserLocalUser getProviderUserLocalUserByProvIdAndProvUserId(String providerId, String providerUserId) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(ProviderUserLocalUser.class);
        criteria.add(Restrictions.and(Restrictions.eq("providerId", providerId),Restrictions.eq("providerUserId", providerUserId) ));
        List<ProviderUserLocalUser> listOfProviderUserLocalUser = (List<ProviderUserLocalUser>) criteria.list();

        ProviderUserLocalUser providerUserLocalUser = null;

        if(!listOfProviderUserLocalUser.isEmpty()) {
            providerUserLocalUser = listOfProviderUserLocalUser.get(0);
        }

        return providerUserLocalUser;
    }

    public List<ProviderUserLocalUser> getProviderUserLocalUserByLocalUserId(String userId) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(ProviderUserLocalUser.class);
        criteria.add(Restrictions.eq("localUserId", userId));

        List<ProviderUserLocalUser> listOfProviderUserLocalUser = (List<ProviderUserLocalUser>) criteria.list();

        return listOfProviderUserLocalUser;
    }
}
