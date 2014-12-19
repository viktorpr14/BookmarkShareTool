package org.softserveinc.rest;

import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("hello")
@Component
public class UserRestService {

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String showProfile() {
            return "Hello World";
        }
}
