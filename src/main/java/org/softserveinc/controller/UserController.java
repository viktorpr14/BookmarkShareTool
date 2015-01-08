package org.softserveinc.controller;

import com.google.gson.Gson;
import org.softserveinc.domain.Team;
import org.softserveinc.domain.User;
import org.softserveinc.domain.UserTeam;
import org.softserveinc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public String showUserProfile() {
        return "userProfile";
    }

    @RequestMapping(value = "/creatingTeam", method = RequestMethod.GET)
    public String getCreateTeamPage(Model model) {

        model.addAttribute("team", new Team());
        return "creatingTeam";
    }

    @RequestMapping(value = "/creatingTeam", method = RequestMethod.POST)
    public String addTeamIntoDB(Team team, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "creatingTeam";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User user = userService.findUserByUsername(name);

        userService.saveTeamIntoDB(team);

        System.out.println("Team ID=" + team.getTeamId());

        UserTeam userTeam = new UserTeam();
//        userTeam.setMember(true);
        userTeam.setUserId(user.getUserId());
        userTeam.setTeam(team);

        team.getUsersTeams().add(userTeam);

        userService.saveUserTeamIntoDB(userTeam);

        model.addAttribute(team);

        return "teamProfile";
    }

    @RequestMapping("/getNotMembersForTeam")
    public void getNotMembersForTeam(HttpServletRequest request, HttpServletResponse response) {
        String teamId = request.getParameter("teamId");

        Map<String, String> idsAndNames = userService.getIdsAndNamesOfNotMembersByTeamId(teamId);

        String json = new Gson().toJson(idsAndNames);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "inviteUser", method = RequestMethod.POST)
    public String inviteUser(HttpServletRequest request, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        String userId = request.getParameter("users");
        String teamId = request.getParameter("teamId");

        Team team = userService.getTeamById(teamId);

        if(Integer.parseInt(userId) > 0) {
            UserTeam userTeam = new UserTeam();
//            userTeam.setMember(false);
            userTeam.setUserId(Integer.parseInt(userId));
            userTeam.setTeam(team);
//            userTeam.setStatus(userName);

            userService.saveUserTeamIntoDB(userTeam);
        }

        model.addAttribute(team);

        return "teamProfile";
    }
}