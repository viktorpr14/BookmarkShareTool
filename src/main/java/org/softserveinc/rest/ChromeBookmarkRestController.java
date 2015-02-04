package org.softserveinc.rest;

import com.google.gson.Gson;
import org.softserveinc.converters.GoogleJsonBuilder;
import org.softserveinc.util.GoogleTreeNodeWrapper;
import org.softserveinc.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class ChromeBookmarkRestController {
    @Inject
    private GoogleJsonBuilder googleJsonBuilder;

    @RequestMapping(value = "/rest/bookmark/google", method = RequestMethod.GET)
    public String getBookmarksForChrome() {
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GoogleTreeNodeWrapper googleTreeNodeWrapper = googleJsonBuilder.buildJson(user.getUserId().toString());
        return new Gson().toJson(googleTreeNodeWrapper);

    }
}
