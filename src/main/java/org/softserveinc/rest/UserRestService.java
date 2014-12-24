package org.softserveinc.rest;

import com.google.gson.Gson;
import org.softserveinc.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/userProfile/{username}")
@Service
public class UserRestService {
    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("username") String userName) {
        if(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")){
            return"Please login";
        }
        return new Gson().toJson(userService.findUserByUsername(userName));
    }
}
