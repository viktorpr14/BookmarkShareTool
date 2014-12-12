package org.softserveinc.service;

import org.softserveinc.domain.Team;
import org.softserveinc.domain.User;
import org.softserveinc.domain.UserTeam;
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


    public void saveTeamIntoDB(Team team) {
        hibernateDAO.saveTeamIntoDB(team);
    }

    public void updateUserInDB(User user) {
        hibernateDAO.updateUserInDB(user);
    }

    public void saveUserTeamIntoDB(UserTeam userTeam) {
        hibernateDAO.saveUserTeamIntoDB(userTeam);
    }
}
