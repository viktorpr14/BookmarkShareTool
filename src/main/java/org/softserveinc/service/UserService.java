package org.softserveinc.service;

import org.softserveinc.domain.Community;
import org.softserveinc.domain.User;
import org.softserveinc.repository.HibernateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vv on 01.12.2014.
 */
@Service
public class UserService {

    @Autowired
    private HibernateDAO hibernateDAO;

    public void saveUserIntoDB(User user) {
        hibernateDAO.saveUserIntoDB(user);
    }

    public User findUserByUsername(String username) {
        return hibernateDAO.findUserByUsername(username);
    }


    public void saveCommunityIntoDB(Community community) {
        hibernateDAO.saveCommunityIntoDB(community);
    }
}
