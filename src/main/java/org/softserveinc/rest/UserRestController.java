package org.softserveinc.rest;

import com.google.gson.Gson;
import org.softserveinc.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class UserRestController {
    @Inject
    private UserService userService;

    @RequestMapping(value = "/rest/userProfile/{username}", method = RequestMethod.GET)
    public String getUser(@PathVariable("username") String userName) {
        if(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")){
            return"Please login";
        }
        return new Gson().toJson(userService.getUserViewByUsername(userName));
    }
}
