package org.softserveinc.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softserveinc.domain.Team;
import org.softserveinc.domain.User;
import org.softserveinc.domain.UserTeam;
import org.softserveinc.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by vv on 29.12.2014.
 */
@RestController
public class TeamRestController {
    @Inject
    private UserService userService;

    @RequestMapping(value = "/rest/teams/{username}", method = RequestMethod.GET)
    public String getTeamsByUserName(@PathVariable("username") String userName) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(userService.findTeamsByUsername(userName));
//        return new Gson().toJson(userService.findTeamsByUsername(userName));
    }

    @RequestMapping(value = "/rest/team/{teamId}", method = RequestMethod.GET)
    public String getTeamById(@PathVariable("teamId") String teamId) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(userService.getTeamById(teamId));
//        return new Gson().toJson(userService.getTeamById(teamId));
    }

    @RequestMapping(value = "/rest/createTeam/{username}", method = RequestMethod.POST)
    public String createNewTeam(@PathVariable("username") String userName, @RequestBody Team newTeam) {
        Team team = new Team();
        team.setTeamName(newTeam.getTeamName());

        userService.saveTeamIntoDB(team);

        User user = userService.findUserByUsername(userName);

        UserTeam userTeam = new UserTeam();
        userTeam.setStatus("owner");
        userTeam.setUserId(user.getUserId());
        userTeam.setTeamId(team.getTeamId());
//        userTeam.setTeam(team);

//        team.getUsersTeams().add(userTeam);

        userService.saveUserTeamIntoDB(userTeam);

        return new Gson().toJson(team.getTeamId());
    }

    @RequestMapping(value = "/rest/notMembers/{teamId}", method = RequestMethod.GET)
    public String getNotMembersByTeamId(@PathVariable("teamId") String teamId) {
        List<User> users = userService.getNotMembersByTeamId(teamId);
        return new Gson().toJson(users);
    }

    @RequestMapping(value = "/rest/inviteUser/{teamId}/{userId}", method = RequestMethod.GET)
    public void inviteUserToTeam(@PathVariable("teamId") String teamId, @PathVariable("userId") String userId) {
        userService.inviteUserToTeam(teamId, userId);
    }

}
