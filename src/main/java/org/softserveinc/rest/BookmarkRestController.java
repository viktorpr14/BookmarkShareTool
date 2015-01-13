package org.softserveinc.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softserveinc.domain.Bookmark;
import org.softserveinc.service.BookmarkService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/rest/bookmarks")
public class BookmarkRestController {
    @Inject
    private BookmarkService bookmarkService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void getTeamsByUserName(@RequestBody Bookmark bookmark) {
        bookmarkService.saveBookmark(bookmark);
    }
}
