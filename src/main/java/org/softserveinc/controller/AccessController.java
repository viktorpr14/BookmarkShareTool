package org.softserveinc.controller;

import org.softserveinc.domain.User;
import org.softserveinc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccessController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required=false) String message) {
        model.addAttribute("message", message);
        return "access/login";
    }

    @RequestMapping(value = "/denied")
    public String denied() {
        return "access/denied";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure() {
        String message = "Login Failure!";
        return "redirect:/login?message="+message;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addUser(User user, Model model) {

        userService.saveUserIntoDB(user);
        return "redirect:/userProfile/"+user.getUsername();
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSingUpPage(Model model) {
        model.addAttribute("user", new User());
        return "access/signup";

    }
}
