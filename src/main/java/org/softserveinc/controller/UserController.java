package org.softserveinc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public String showUserProfile() {
        return "userProfile";
    }

}