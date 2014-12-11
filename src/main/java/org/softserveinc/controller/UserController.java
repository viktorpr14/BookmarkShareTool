package org.softserveinc.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "signup";
        }
        model.addAttribute(user);

        userService.saveUserIntoDB(user);
        
        return "userProfile";
    }

    @RequestMapping(value = "/userProfile/{username}", method = RequestMethod.GET)
    public String showUserProfile(@PathVariable String username, Model model) {

        model.addAttribute(userService.findUserByUsername(username));
        return "userProfile";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSingUpPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public String showUserProfile( Model model) {
        model.addAttribute(userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "userProfile";
    }
    @RequestMapping(value = "/creatingTeam", method = RequestMethod.GET)
    public String getCreateTeamPage(Model model) {

//        System.out.println(user.getUserId());
//        System.out.println(user.getFirstName());
//        System.out.println(user.getEmail());
        model.addAttribute("team", new Team());
        return "creatingTeam";
    }
    @RequestMapping(value = "/creatingTeam", method = RequestMethod.POST)
    public String addTeamIntoDB(Team team, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "creatingTeam";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println(name);

//        Map<String, Object> map = model.asMap();
//        for (String s : map.keySet()) {
//            System.out.println("Key = " + s);
//            System.out.println("Value = " + map.get(s));
//            System.out.println();
//        }

        User user = userService.findUserByUsername(name);

//        System.out.println(user.getUserId());
//        System.out.println(user.getFirstName());
//        System.out.println(user.getEmail());

        //Collection<Team> usersCommunities = user.getUsersTeams().;
//        for (Team usersCommunity : usersCommunities) {
//            System.out.println(usersCommunity.getTeamName());
//        }

        //usersCommunities.add(team);

//        System.out.println("------------------");
//        for (Team usersCommunity : usersCommunities) {
//            System.out.println(usersCommunity.getTeamName());
//        }
//        System.out.println("------------------");

        //userService.updateUserInDB(user);

//        System.out.println("OK!!!!!!!!!!");

//        userService.saveTeamIntoDB(team);

        userService.saveTeamIntoDB(team);

        UserTeam userTeam = new UserTeam();
        userTeam.setMember(true);
        userTeam.setUser(user);
        userTeam.setTeam(team);

        team.getUsersTeams().add(userTeam);
        user.getUsersTeams().add(userTeam);

        userService.updateUserInDB(user);

        model.addAttribute(team);

        return "teamProfile";
    }

}