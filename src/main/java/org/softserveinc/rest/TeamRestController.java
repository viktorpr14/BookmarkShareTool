package org.softserveinc.rest;

import com.google.gson.Gson;
import org.softserveinc.domain.Team;
import org.softserveinc.domain.User;
import org.softserveinc.domain.UserTeam;
import org.softserveinc.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by vv on 29.12.2014.
 */
@RestController
public class TeamRestController {
    @Inject
    private UserService userService;

    @RequestMapping(value = "/rest/teams/{username}", method = RequestMethod.GET)
    public String getTeamsByUserName(@PathVariable("username") String userName) {
        return new Gson().toJson(userService.findTeamsByUsername(userName));
    }

    @RequestMapping(value = "/rest/team/{teamId}", method = RequestMethod.GET)
    public String getTeamById(@PathVariable("teamId") String teamId) {
        return new Gson().toJson(userService.getTeamById(teamId));
    }

    @RequestMapping(value = "/rest/createTeam/{username}", method = RequestMethod.POST)
    public String createNewTeam(@PathVariable("username") String userName, @RequestBody Team newTeam) {
        Team team = new Team();
        team.setTeamName(newTeam.getTeamName());

        userService.saveTeamIntoDB(team);

        User user = userService.findUserByUsername(userName);

        UserTeam userTeam = new UserTeam();
        userTeam.setMember(true);
        userTeam.setUserId(user.getUserId());
        userTeam.setTeam(team);

        team.getUsersTeams().add(userTeam);

        userService.saveUserTeamIntoDB(userTeam);

        return new Gson().toJson(team.getTeamId());
    }

}
