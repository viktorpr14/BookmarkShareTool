package org.softserveinc.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

@ApplicationPath("rest")
public class BookmarksToolApp extends ResourceConfig {

    public BookmarksToolApp() {
        register(RequestContextFilter.class);
        register(UserRestService.class);
    }
}

