package org.softserveinc.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softserveinc.domain.Team;
import org.softserveinc.domain.User;
import org.softserveinc.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class TeamRestController {
    @Inject
    private UserService userService;

    @RequestMapping(value = "/rest/teams/{username}", method = RequestMethod.GET)
    public String getTeamsByUserName(@PathVariable("username") String userName) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(userService.findTeamsWhereUserIsMemberOrOwnerByUsername(userName));
    }

    @RequestMapping(value = "/rest/invitations/{username}", method = RequestMethod.GET)
    public String getInvitationsByUserName(@PathVariable("username") String userName) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(userService.getInvitationsByUsername(userName));
    }

    @RequestMapping(value = "/rest/team/{teamId}", method = RequestMethod.GET)
    public String getTeamById(@PathVariable("teamId") String teamId) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(userService.getTeamById(teamId));
    }

    @RequestMapping(value = "/rest/createTeam/{username}", method = RequestMethod.POST)
    public String createNewTeam(@PathVariable("username") String userName, @RequestBody Team newTeam) {
        Team team = userService.createNewTeam(userName, newTeam);
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

    @RequestMapping(value = "/rest/acceptInvitation/{userTeamId}", method = RequestMethod.PUT)
    public void acceptInvitation(@PathVariable("userTeamId") String userTeamId) {
        userService.acceptInvitation(userTeamId);
    }

    @RequestMapping(value = "/rest/rejectInvitation/{userTeamId}", method = RequestMethod.PUT)
    public void rejectInvitation(@PathVariable("userTeamId") String userTeamId) {
        userService.rejectedInvitation(userTeamId);
    }

}
