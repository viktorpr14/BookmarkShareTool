package org.softserveinc.controller;

import org.softserveinc.domain.Community;
import org.softserveinc.domain.User;
import org.softserveinc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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

    @RequestMapping(value = "/createCommunity", method = RequestMethod.GET)
    public String getCreateCommunityPage(Model model) {
        model.addAttribute("community", new Community());
        return "creatingCommunity";
    }
    @RequestMapping(value = "/createCommunity", method = RequestMethod.POST)
    public String addCommunityIntoDB(Community community, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "creatingCommunity";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println(name);

        Map<String, Object> map = model.asMap();
        for (String s : map.keySet()) {
            System.out.println("Key = " + s);
            System.out.println("Value = " + map.get(s));
            System.out.println();
        }

        userService.saveCommunityIntoDB(community);
        model.addAttribute(community);

        return "communityProfile";
    }
    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public String showUserProfile() {
        return "userProfile";
    }
}
