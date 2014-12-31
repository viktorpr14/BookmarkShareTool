package org.softserveinc.rest;

import com.google.gson.Gson;
import org.softserveinc.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
