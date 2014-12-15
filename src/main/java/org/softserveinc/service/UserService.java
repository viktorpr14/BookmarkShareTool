package org.softserveinc.service;

import org.softserveinc.domain.Team;
import org.softserveinc.domain.User;
import org.softserveinc.domain.UserTeam;
import org.softserveinc.repository.HibernateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Map<String, String> getIdsAndNamesOfNotMembersByTeamId(String teamId) {
        Map<String, String> idsAndNames = new HashMap<String, String>();

        List<User> users = getNotMembersByTeamId(teamId);

        if(!users.isEmpty()) {
            for (User user : users) {
                idsAndNames.put(String.valueOf(user.getUserId()),user.getUsername());
            }
        } else {
            idsAndNames.put("-1", "no new users");
        }

        return idsAndNames;
    }

    private List<User> getNotMembersByTeamId(String teamId) {
//        List<User> allUsers = hibernateDAO.getAllUsers();

        Set<Integer> idsOfMembers = new HashSet<Integer>();
//        Set<Integer> idsOfNotMembers = new HashSet<Integer>();

        Team team = hibernateDAO.getTeamById(teamId);
        Set<UserTeam> userTeams = team.getUsersTeams();
        for (UserTeam userTeam : userTeams) {
            idsOfMembers.add(userTeam.getUserId());
        }

//        List<UserTeam> notBelongingUserTeamObjects = hibernateDAO.getAllUserTeamObjectsNotBelongingForTeam(teamId);
//        for (UserTeam userTeamObject : notBelongingUserTeamObjects) {
//            idsOfNotMembers.add(userTeamObject.getUserId());
//        }

//        List<User> users = hibernateDAO.getUsersByUserIds(idsOfNotMembers);
//        List<String> idsOfNotBelongingUsers = hibernateDAO.getIdsOfNotBelongingUsersForTeam(teamId);

        List<User> users = hibernateDAO.getUsersExceptMembers(idsOfMembers);

        return users;
    }
}
